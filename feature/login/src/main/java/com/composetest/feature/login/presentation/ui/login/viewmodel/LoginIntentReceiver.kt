package com.composetest.feature.login.presentation.ui.login.viewmodel

import com.composetest.core.domain.enums.Theme
import com.composetest.core.security.androidapi.enums.BiometricError
import com.composetest.core.ui.interfaces.IntentReceiver

internal interface LoginIntentReceiver : IntentReceiver<LoginIntentReceiver> {
    fun writeData(email: String? = null, password: String? = null)
    fun setStatusBarsTheme(currentAppTheme: Theme, systemIsDarkMode: Boolean)
    fun checkShowInvalidEmailMsg(hasFocus: Boolean)
    fun login(byBiometric: Boolean)
    fun biometricErrorAnimationFinished()
    fun biometricErrorHandler(biometricError: BiometricError)
    fun showBiometricPrompt()
}