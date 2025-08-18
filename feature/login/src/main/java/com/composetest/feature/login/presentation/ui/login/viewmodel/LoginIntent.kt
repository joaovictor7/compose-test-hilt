package com.composetest.feature.login.presentation.ui.login.viewmodel

import com.composetest.core.domain.enums.Theme
import com.composetest.core.security.androidapi.enums.BiometricError
import com.composetest.core.ui.interfaces.Intent

internal sealed interface LoginIntent : Intent<LoginIntentReceiver> {
    data class WriteData(
        private val email: String? = null,
        private val password: String? = null
    ) : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.writeData(email, password)
        }
    }

    data class SetStatusBarsTheme(
        private val currentAppTheme: Theme,
        private val systemIsDarkMode: Boolean,
    ) : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.setStatusBarsTheme(currentAppTheme, systemIsDarkMode)
        }
    }

    data class CheckShowInvalidEmailMsg(private val hasFocus: Boolean) : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.checkShowInvalidEmailMsg(hasFocus)
        }
    }

    data class BiometricErrorHandler(private val biometricError: BiometricError) : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.biometricErrorHandler(biometricError)
        }
    }

    data class Login(private val byBiometric: Boolean) : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.login(byBiometric)
        }
    }

    data object BiometricErrorAnimationFinished : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.biometricErrorAnimationFinished()
        }
    }

    data object ShowBiometricPrompt : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.showBiometricPrompt()
        }
    }
}