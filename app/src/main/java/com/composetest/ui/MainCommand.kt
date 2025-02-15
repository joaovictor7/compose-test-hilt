package com.composetest.ui

import com.composetest.core.ui.interfaces.Command

internal sealed interface MainCommand : Command<MainCommandReceiver> {
    data class VerifySession(private val currentRoute: String?) : MainCommand {
        override fun execute(commandReceiver: MainCommandReceiver) {
            commandReceiver.verifySession(currentRoute)
        }
    }

    data object FetchRemoteConfig : MainCommand {
        override fun execute(commandReceiver: MainCommandReceiver) {
            commandReceiver.fetchRemoteConfig()
        }
    }

    data object DismissAlertDialog : MainCommand {
        override fun execute(commandReceiver: MainCommandReceiver) {
            commandReceiver.dismissAlertDialog()
        }
    }
}