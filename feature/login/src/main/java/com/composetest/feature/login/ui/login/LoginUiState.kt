package com.composetest.feature.login.ui.login

import com.composetest.core.designsystem.enums.textfields.TextFieldIcon
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.login.R
import com.composetest.feature.login.enums.LoginButtonState
import com.composetest.feature.login.models.LoginFormModel

internal data class LoginUiState(
    val loginFormModel: LoginFormModel = LoginFormModel(),
    val needsLogin: Boolean = false,
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val loginButtonState: LoginButtonState = LoginButtonState.BUTTON_DISABLE,
    val invalidCredentials: Boolean = false,
    val simpleDialogParam: SimpleDialogParam? = null,
    override var isLoading: Boolean = false
) : BaseUiState {
    val emailTrailingIcon get() = if (invalidEmail) TextFieldIcon.ERROR else null
    val emailSupporting get() = if (invalidEmail) R.string.feature_login_invalid_email else null
    val loginButtonEnabled get() = loginButtonState == LoginButtonState.BUTTON_ENABLED
    val useBiometric get() = loginButtonState == LoginButtonState.BIOMETRIC

    fun initUiState(
        versionName: String,
        loginButtonState: LoginButtonState
    ) = copy(
        needsLogin = true,
        versionName = versionName,
        loginButtonState = loginButtonState
    )

    fun setLoginForm(
        loginFormModel: LoginFormModel,
        loginButtonState: LoginButtonState
    ) = copy(
        loginFormModel = loginFormModel,
        loginButtonState = loginButtonState,
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