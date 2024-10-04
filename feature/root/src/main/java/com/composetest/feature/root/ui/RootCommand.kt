package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.root.enums.NavigationBottomBar

internal sealed interface RootCommand : Command<RootCommandReceiver> {
    data class SetSelectedNavigationBottomBar(
        private val selectedNavigationBottomBar: NavigationBottomBar
    ) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.setSelectedNavigationBottomBar(selectedNavigationBottomBar)
        }
    }

    data class SetRootNavGraph(private val navController: NavHostController) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.setRootNavGraph(navController)
        }
    }

    data object BackHandler : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.backHandler()
        }
    }
}