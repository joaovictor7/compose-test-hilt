package com.composetest.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.Command

internal sealed interface MainCommand : Command<MainCommandReceiver> {
    data object VerifySession : MainCommand {
        override fun execute(commandReceiver: MainCommandReceiver) {
            commandReceiver.verifySession()
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

    data class SetNavigationGraph(private val navController: NavHostController) : MainCommand {
        override fun execute(commandReceiver: MainCommandReceiver) {
            commandReceiver.setMainNavGraph(navController)
        }
    }
}