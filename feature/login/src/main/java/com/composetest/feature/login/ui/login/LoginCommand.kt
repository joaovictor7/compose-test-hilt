package com.composetest.feature.login.ui.login

import com.composetest.core.domain.enums.Theme
import com.composetest.core.ui.interfaces.Command

internal sealed interface LoginCommand : Command<LoginCommandReceiver> {
    data class WriteData(
        val email: String? = null,
        val password: String? = null
    ) : LoginCommand {
        override fun execute(commandReceiver: LoginCommandReceiver) {
            commandReceiver.writeData(email, password)
        }
    }

    data class SetCustomTheme(
        val enterScreen: Boolean,
        val currentAppTheme: Theme
    ) : LoginCommand {
        override fun execute(commandReceiver: LoginCommandReceiver) {
            commandReceiver.setCustomTheme(enterScreen, currentAppTheme)
        }
    }

    data class CheckShowInvalidEmailMsg(private val hasFocus: Boolean) : LoginCommand {
        override fun execute(commandReceiver: LoginCommandReceiver) {
            commandReceiver.checkShowInvalidEmailMsg(hasFocus)
        }
    }

    data object DismissSimpleDialog : LoginCommand {
        override fun execute(commandReceiver: LoginCommandReceiver) {
            commandReceiver.dismissSimpleDialog()
        }
    }

    data object Login : LoginCommand {
        override fun execute(commandReceiver: LoginCommandReceiver) {
            commandReceiver.login()
        }
    }
}