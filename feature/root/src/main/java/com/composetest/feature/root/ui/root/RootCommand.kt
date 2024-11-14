package com.composetest.feature.root.ui.root

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.root.enums.NavigationFeature

internal sealed interface RootCommand : Command<RootCommandReceiver> {
    data class SetSelectedBottomNavigationFeature(
        private val selectedNavigationBottomBar: NavigationFeature
    ) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.setSelectedBottomNavigationFeature(selectedNavigationBottomBar)
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