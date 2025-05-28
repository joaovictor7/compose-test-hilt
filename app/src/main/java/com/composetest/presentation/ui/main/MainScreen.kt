package com.composetest.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.component.dialog.SimpleDialog
import com.composetest.core.designsystem.component.lifecycle.LifecycleEvent
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.extension.currentRoute
import com.composetest.core.router.extension.navigateTo
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.navigation.DialogNavGraphs
import com.composetest.navigation.ScreenNavGraphs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun MainScreen(
    uiState: MainUiState,
    uiEvent: Flow<MainUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<MainIntentReceiver>) -> Unit = {}
) {
    if (uiState.firstDestination == null) return
    ComposeTestTheme(
        dynamicColor = uiState.appTheme.dynamicColor,
        theme = uiState.appTheme.theme
    ) {
        DialogHandler(uiState = uiState, onExecuteIntent = onExecuteIntent)
        Navigation(
            uiEvent = uiEvent,
            onExecuteIntent = onExecuteIntent,
            firstScreenDestination = uiState.firstDestination
        )
    }
}

@Composable
private fun Navigation(
    uiEvent: Flow<MainUiEvent>,
    onExecuteIntent: (Intent<MainIntentReceiver>) -> Unit,
    firstScreenDestination: Destination
) {
    val navController = rememberNavController()
    val screenNavGraphs by ScreenNavGraphs(navController)
    val dialogNavGraphs by DialogNavGraphs(navController)
    NavHost(navController = navController, startDestination = firstScreenDestination) {
        listOf(screenNavGraphs, dialogNavGraphs).flatten().forEach { it() }
    }
    UiEventsHandler(uiEvent = uiEvent, navController = navController)
    LifecycleHandler(onExecuteIntent = onExecuteIntent, navController = navController)
}

@Composable
private fun LifecycleHandler(
    onExecuteIntent: (Intent<MainIntentReceiver>) -> Unit,
    navController: NavHostController
) {
    LifecycleEvent {
        if (it == Lifecycle.Event.ON_RESUME) {
            onExecuteIntent(MainIntent.VerifySession(navController.currentRoute))
        }
    }
}

@Composable
private fun DialogHandler(
    uiState: MainUiState,
    onExecuteIntent: (Intent<MainIntentReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteIntent(MainIntent.DismissAlertDialog)
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