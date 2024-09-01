package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.Command

internal sealed interface RootCommand : Command<RootCommandReceiver> {
    data class ChangeSelectedDockItem(private val selectedIndex: Int) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.changeSelectedDockItem(selectedIndex)
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