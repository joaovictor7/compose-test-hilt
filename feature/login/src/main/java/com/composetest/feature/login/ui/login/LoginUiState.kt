package com.composetest.feature.login.ui.login

import androidx.annotation.StringRes
import com.composetest.core.designsystem.enums.textfields.TextFieldIcons
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.designsystem.params.textfields.TextFieldTrailingIconParam
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
    val emailTrailingIconTextField
        get() = if (invalidEmail)
            TextFieldTrailingIconParam(iconType = TextFieldIcons.ERROR) else null
    val emailSupportingTextField
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

    fun setLoginForm(email: String?, password: String?): LoginUiState {
        var newLoginFormModel = loginFormModel
        email?.let {
            newLoginFormModel = newLoginFormModel.copy(email = it)
        }
        password?.let {
            newLoginFormModel = newLoginFormModel.copy(password = it)
        }
        return this.copy(loginFormModel = newLoginFormModel)
    }

    fun setInvalidEmail(invalid: Boolean) = copy(invalidEmail = invalid)

    fun setEnabledButton(enable: Boolean) =
        copy(invalidCredentials = false, enableLoginButton = enable)

    fun setShowInvalidCredentialsMsg(show: Boolean) = copy(invalidCredentials = show)

    fun setLoading(isLoading: Boolean) = copy(
        isLoading = isLoading,
        invalidCredentials = if (isLoading) false else invalidCredentials
    )

    fun setSimpleDialog(simpleDialogParam: SimpleDialogParam?) = copy(
        simpleDialogParam = simpleDialogParam
    )
}