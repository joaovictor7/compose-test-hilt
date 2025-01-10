package com.composetest.feature.root.ui.root

import androidx.compose.material3.DrawerValue
import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.root.enums.NavigationFeature

internal sealed interface RootCommand : Command<RootCommandReceiver> {
    data class NavigateToFeature(private val feature: NavigationFeature) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.navigateToFeature(feature)
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

    data class ModalDrawerManager(private val drawerValue: DrawerValue) : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.modalDrawerManager(drawerValue)
        }
    }

    data object Logout : RootCommand {
        override fun execute(commandReceiver: RootCommandReceiver) {
            commandReceiver.logout()
        }
    }
}