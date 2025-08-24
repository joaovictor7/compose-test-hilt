package com.composetest.feature.findroute.presenter.ui.findroute

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteIntentReceiver
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteUiEvent
import com.composetest.feature.findroute.presenter.ui.findroute.viewmodel.FindRouteUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun NewsListScreen(
    uiState: FindRouteUiState,
    uiEvent: Flow<FindRouteUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<FindRouteIntentReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    UiEventsHandler(uiEvent = uiEvent, navController = navController)
}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<FindRouteUiEvent>,
    navController: NavHostController
) {
    UiEventsObserver(uiEvent) {

    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        NewsListScreen(
            uiState = FindRouteUiState(),
        )
    }
}