package com.composetest.feature.login.presenter.ui.login

import com.composetest.core.domain.enums.Theme
import com.composetest.core.security.enums.BiometricError
import com.composetest.core.ui.interfaces.IntentReceiver

internal interface LoginIntentReceiver : IntentReceiver<LoginIntentReceiver> {
    fun writeData(email: String? = null, password: String? = null)
    fun setStatusBarsTheme(enterScreen: Boolean, currentAppTheme: Theme)
    fun checkShowInvalidEmailMsg(hasFocus: Boolean)
    fun login(byBiometric: Boolean)
    fun biometricErrorAnimationFinished()
    fun dismissSimpleDialog()
    fun biometricErrorHandler(biometricError: BiometricError)
    fun showBiometricPrompt()
}