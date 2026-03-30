package com.composetest.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.composetest.core.designsystem.component.lifecycle.LifecycleEvent
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.extension.currentNavKey
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.presentation.ui.main.viewmodel.MainIntent
import com.composetest.presentation.ui.main.viewmodel.MainIntentReceiver
import com.composetest.presentation.ui.main.viewmodel.MainUiEvent
import com.composetest.presentation.ui.main.viewmodel.MainUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun MainScreen(
    uiState: MainUiState,
    uiEvent: Flow<MainUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<MainIntentReceiver>) -> Unit = {}
) {
    ComposeTestTheme(
        dynamicColor = uiState.appTheme.dynamicColor,
        theme = uiState.appTheme.theme,
    ) {
        Navigation(
            uiState = uiState,
            uiEvent = uiEvent,
            onExecuteIntent = onExecuteIntent,
        )
    }
}

@Composable
private fun Navigation(
    uiState: MainUiState,
    uiEvent: Flow<MainUiEvent>,
    onExecuteIntent: (Intent<MainIntentReceiver>) -> Unit,
) {
    if (uiState.firstNavKey == null) return
    val navBackStack = rememberNavBackStack(uiState.firstNavKey)
    NavDisplay(
        backStack = navBackStack,
        entryProvider = entryProvider {
            uiState.navGraphs.forEach { navGraph ->
                navGraph.run { registerEntries(navBackStack) }
            }
        }
    )
    UiEventsHandler(uiEvent = uiEvent, navBackStack = navBackStack)
    LifecycleHandler(onExecuteIntent = onExecuteIntent, backStack = navBackStack)
}

@Composable
private fun LifecycleHandler(
    onExecuteIntent: (Intent<MainIntentReceiver>) -> Unit,
    backStack: NavBackStack<NavKey>,
) {
    LifecycleEvent(onResume = {
        onExecuteIntent(MainIntent.VerifySession(backStack.currentNavKey))
    })
}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<MainUiEvent>,
    navBackStack: NavBackStack<NavKey>,
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is MainUiEvent.NavigateTo -> navBackStack.plus(it.navigationModel)
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
