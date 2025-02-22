package com.composetest.feature.login.ui.login

import com.composetest.core.domain.enums.Theme
import com.composetest.core.security.enums.BiometricError
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface LoginCommandReceiver : CommandReceiver<LoginCommandReceiver> {
    fun writeData(email: String? = null, password: String? = null)
    fun setStatusBarsTheme(enterScreen: Boolean, currentAppTheme: Theme)
    fun checkShowInvalidEmailMsg(hasFocus: Boolean)
    fun login(byBiometric: Boolean)
    fun biometricErrorAnimationFinished()
    fun dismissSimpleDialog()
    fun biometricErrorHandler(biometricError: BiometricError)
    fun showBiometricPrompt()
}