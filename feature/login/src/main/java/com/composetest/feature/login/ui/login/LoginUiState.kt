package com.composetest.feature.login.ui.login

import com.composetest.core.designsystem.enums.textfields.TextFieldIcon
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.feature.login.R
import com.composetest.feature.login.models.BiometricModel
import com.composetest.feature.login.models.LoginFormModel

internal data class LoginUiState(
    val loginFormModel: LoginFormModel = LoginFormModel(),
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val loginButtonIsEnabled: Boolean = false,
    val invalidCredentials: Boolean = false,
    val biometricModel: BiometricModel? = null,
    val simpleDialogParam: SimpleDialogParam? = null,
    val isLoading: Boolean = false
) {
    val emailTrailingIcon get() = if (invalidEmail) TextFieldIcon.ERROR else null
    val emailSupporting get() = if (invalidEmail) R.string.feature_login_invalid_email else null

    fun initUiState(
        simpleDialogParam: SimpleDialogParam?,
        versionName: String,
        loginButtonIsEnabled: Boolean,
        biometricModel: BiometricModel?,
    ) = copy(
        simpleDialogParam = simpleDialogParam,
        versionName = versionName,
        loginButtonIsEnabled = loginButtonIsEnabled,
        biometricModel = biometricModel
    )

    fun setLoginForm(loginFormModel: LoginFormModel, loginButtonEnabled: Boolean) = copy(
        loginFormModel = loginFormModel,
        loginButtonIsEnabled = loginButtonEnabled,
        invalidCredentials = false,
    )

    fun setInvalidEmail(invalid: Boolean) = copy(invalidEmail = invalid)

    fun setShowInvalidCredentialsMsg(show: Boolean) = copy(invalidCredentials = show)

    fun setBiometricModel(biometricModel: BiometricModel) = copy(biometricModel = biometricModel)

    fun setLoading(isLoading: Boolean) = copy(
        isLoading = isLoading,
        invalidCredentials = if (isLoading) false else invalidCredentials
    )

    fun setSimpleDialog(simpleDialogParam: SimpleDialogParam?) = copy(
        simpleDialogParam = simpleDialogParam
    )
}