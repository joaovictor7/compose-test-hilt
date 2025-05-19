package com.composetest.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.lifecycle.LifecycleEvent
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.extensions.currentRoute
import com.composetest.core.router.extensions.navigateTo
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.utils.UiEventsObserver
import com.composetest.navigation.DialogNavGraphs
import com.composetest.navigation.ScreenNavGraphs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun MainScreen(
    uiState: MainUiState,
    uiEvent: Flow<MainUiEvent> = emptyFlow(),
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit = {}
) {
    if (uiState.firstDestination == null) return
    ComposeTestTheme(
        dynamicColor = uiState.appTheme.dynamicColor,
        theme = uiState.appTheme.theme
    ) {
        DialogHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
        Navigation(
            uiEvent = uiEvent,
            onExecuteCommand = onExecuteCommand,
            firstScreenDestination = uiState.firstDestination
        )
    }
}

@Composable
private fun Navigation(
    uiEvent: Flow<MainUiEvent>,
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit,
    firstScreenDestination: Destination
) {
    val navController = rememberNavController()
    val screenNavGraphs by ScreenNavGraphs(navController)
    val dialogNavGraphs by DialogNavGraphs(navController)
    NavHost(navController = navController, startDestination = firstScreenDestination) {
        listOf(screenNavGraphs, dialogNavGraphs).flatten().forEach { it() }
    }
    UiEventsHandler(uiEvent = uiEvent, navController = navController)
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
private fun UiEventsHandler(
    uiEvent: Flow<MainUiEvent>,
    navController: NavHostController,
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is MainUiEvent.NavigateTo -> navController.navigateTo(it.navigationModel)
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        MainScreen(uiState = MainUiState())
    }
}