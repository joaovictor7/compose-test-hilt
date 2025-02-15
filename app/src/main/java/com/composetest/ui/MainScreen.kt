package com.composetest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.lifecycle.LifecycleEvent
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.extensions.currentRoute
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.ui.interfaces.Command
import com.composetest.navigation.NavGraphs

@Composable
internal fun MainScreen(
    uiState: MainUiState,
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit = {}
) {
    ComposeTestTheme(
        dynamicColor = uiState.theme.dynamicColor,
        theme = uiState.theme.theme
    ) {
        DialogHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
        Navigation(
            onExecuteCommand = onExecuteCommand,
            firstScreenDestination = LoginDestination(),
        )
    }
}

@Composable
private fun Navigation(
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit,
    firstScreenDestination: Destination
) {
    val navController = rememberNavController()
    val navGraphs by NavGraphs(navController)
    NavHost(navController = navController, startDestination = firstScreenDestination) {
        navGraphs.forEach { it() }
    }
    LifecycleHandler(onExecuteCommand = onExecuteCommand, navController = navController)
}

@Composable
private fun LifecycleHandler(
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit,
    navController: NavHostController
) {
    LifecycleEvent {
        if (it == Lifecycle.Event.ON_RESUME) {
            onExecuteCommand(MainCommand.VerifySession(navController.currentRoute))
        }
    }
}

@Composable
private fun DialogHandler(
    uiState: MainUiState,
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteCommand(MainCommand.DismissAlertDialog)
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        MainScreen(uiState = MainUiState())
    }
}