package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.Command

internal sealed interface RootCommand : Command<RootCommandReceiver> {
    data class ChangeDockItemSelected(private val selectedIndex: Int) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.changeDockItemSelected(selectedIndex)
        }
    }

    data class SetRootNavGraph(private val navController: NavHostController) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.setRootNavGraph(navController)
        }
    }
}