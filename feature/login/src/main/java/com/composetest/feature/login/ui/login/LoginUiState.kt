package com.composetest.feature.login.ui.login

import androidx.annotation.StringRes
import com.composetest.core.designsystem.enums.textfields.TextFieldIcon
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.login.R
import com.composetest.feature.login.models.LoginFormModel

internal data class LoginUiState(
    val loginFormModel: LoginFormModel = LoginFormModel(),
    val needsLogin: Boolean = false,
    val versionName: String = String(),
    @StringRes val distributionTextId: Int? = null,
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val invalidCredentials: Boolean = false,
    val simpleDialogParam: SimpleDialogParam? = null,
    override var isLoading: Boolean = false
) : BaseUiState {
    val emailTrailingIcon
        get() = if (invalidEmail) TextFieldIcon.ERROR else null
    val emailSupporting
        get() = if (invalidEmail) R.string.feature_login_invalid_email else null

    fun initUiState(
        versionName: String,
        @StringRes distributionTextId: Int,
        enableLoginButton: Boolean
    ) = copy(
        needsLogin = true,
        versionName = versionName,
        distributionTextId = distributionTextId,
        enableLoginButton = enableLoginButton
    )

    fun setLoginForm(
        loginFormModel: LoginFormModel,
        enableLoginButton: Boolean
    ) = copy(
        loginFormModel = loginFormModel,
        enableLoginButton = enableLoginButton,
        invalidCredentials = false,
    )

    fun setInvalidEmail(invalid: Boolean) = copy(invalidEmail = invalid)

    fun setShowInvalidCredentialsMsg(show: Boolean) = copy(invalidCredentials = show)

    fun setLoading(isLoading: Boolean) = copy(
        isLoading = isLoading,
        invalidCredentials = if (isLoading) false else invalidCredentials
    )

    fun setSimpleDialog(simpleDialogParam: SimpleDialogParam?) = copy(
        simpleDialogParam = simpleDialogParam
    )
}