package com.composetest.feature.login.ui.login

import com.composetest.core.domain.enums.Theme
import com.composetest.core.ui.interfaces.Command

internal sealed interface LoginCommands : Command<LoginCommandReceiver> {
    data class WriteData(
        val email: String? = null,
        val password: String? = null
    ) : LoginCommands {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.writeData(email, password)
        }
    }

    data class SetCustomTheme(
        val enterScreen: Boolean,
        val currentAppTheme: Theme
    ) : LoginCommands {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.setCustomTheme(enterScreen, currentAppTheme)
        }
    }

    data object CheckShowInvalidEmailMsg : LoginCommands {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.checkShowInvalidEmailMsg()
        }
    }

    data object Login : LoginCommands {
        override fun execute(receiver: LoginCommandReceiver) {
            receiver.login()
        }
    }
}