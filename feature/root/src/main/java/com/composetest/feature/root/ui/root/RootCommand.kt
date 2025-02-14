package com.composetest.feature.root.ui.root

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.root.enums.NavigationFeature

internal sealed interface RootCommand : Command<RootCommandReceiver> {
    data class NavigateToFeature(private val navigationFeature: NavigationFeature) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.navigateToFeature(navigationFeature)
        }
    }

    data class CurrentScreenObservable(private val currentScreen: String?) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.currentScreenObservable(currentScreen)
        }
    }

    data object BackHandler : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.backHandler()
        }
    }

    data object Logout : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.logout()
        }
    }
}