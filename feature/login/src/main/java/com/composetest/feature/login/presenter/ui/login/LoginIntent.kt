package com.composetest.feature.login.presenter.ui.login

import com.composetest.core.domain.enums.Theme
import com.composetest.core.security.enums.BiometricError
import com.composetest.core.ui.interfaces.Intent

internal sealed interface LoginIntent : Intent<LoginIntentReceiver> {
    data class WriteData(
        val email: String? = null,
        val password: String? = null
    ) : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.writeData(email, password)
        }
    }

    data class SetStatusBarsTheme(
        val enterScreen: Boolean,
        val currentAppTheme: Theme
    ) : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.setStatusBarsTheme(enterScreen, currentAppTheme)
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

    data object DismissSimpleDialog : LoginIntent {
        override fun execute(intentReceiver: LoginIntentReceiver) {
            intentReceiver.dismissSimpleDialog()
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