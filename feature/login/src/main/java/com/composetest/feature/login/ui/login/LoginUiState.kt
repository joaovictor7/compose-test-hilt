package com.composetest.feature.login.ui.login

import com.composetest.core.designsystem.params.alertdialogs.DefaultAlertDialogParam
import com.composetest.core.designsystem.enums.textfields.TextFieldIcons
import com.composetest.core.designsystem.params.textfields.TextFieldTrailingIconParam
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.login.R
import com.composetest.feature.login.models.LoginFormModel

internal data class LoginUiState(
    val loginFormModel: LoginFormModel = LoginFormModel(),
    val needsLogin: Boolean = false,
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val invalidCredentials: Boolean = false,
    val defaultAlertDialogParam: DefaultAlertDialogParam? = null,
    override var isLoading: Boolean = false
) : BaseUiState {
    val emailTrailingIconTextField
        get() = if (invalidEmail)
            TextFieldTrailingIconParam(iconType = TextFieldIcons.ERROR) else null
    val emailSupportingTextField
        get() = if (invalidEmail)
            R.string.feature_login_invalid_email else null

    fun initState(
        versionName: String,
        enableLoginButton: Boolean
    ) = copy(
        needsLogin = true,
        versionName = versionName,
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

    fun setEnabledButton(enable: Boolean) = copy(enableLoginButton = enable)

    fun setShowInvalidCredentialsMsg(show: Boolean) = copy(invalidCredentials = show)

    fun setLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun setDefaultAlertDialog(defaultAlertDialogParam: DefaultAlertDialogParam?) = copy(
        defaultAlertDialogParam = defaultAlertDialogParam
    )
}