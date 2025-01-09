package com.composetest.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.utils.lifecycleEvent
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.navigation.configurationNavGraph
import com.composetest.feature.home.navigation.homeNavGraph
import com.composetest.feature.login.navigation.loginNavGraph
import com.composetest.feature.news.navigation.newsNavGraph
import com.composetest.feature.profile.navigation.profileNavGraph
import com.composetest.feature.root.navigation.rootNavGraph
import com.composetest.feature.weatherforecast.navigation.weatherForecastNavGraph
import kotlinx.coroutines.flow.Flow

internal object MainScreen : Screen<MainUiState, MainUiEvent, MainCommandReceiver> {
    @Composable
    override operator fun invoke(
        uiState: MainUiState,
        uiEvent: Flow<MainUiEvent>?,
        onExecuteCommand: (Command<MainCommandReceiver>) -> Unit
    ) {
        LifecycleHandle(onExecuteCommand)
        ComposeTestTheme(
            dynamicColor = uiState.theme.dynamicColor,
            theme = uiState.theme.theme
        ) {
            DialogsHandle(uiState = uiState, onExecuteCommand = onExecuteCommand)
            Navigation(
                firstScreenDestination = LoginDestination,
                onExecuteCommand = onExecuteCommand
            )
        }
    }
}

@Composable
private fun Navigation(
    firstScreenDestination: Destination,
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit
) {
    val navController = rememberNavController()
    onExecuteCommand(MainCommand.SetNavigationGraph(navController))
    NavHost(
        navController = navController,
        startDestination = firstScreenDestination
    ) {
        loginNavGraph()
        rootNavGraph()
        homeNavGraph()
        profileNavGraph()
        configurationNavGraph()
        weatherForecastNavGraph()
        newsNavGraph()
    }
}

@Composable
private fun LifecycleHandle(onExecuteCommand: (Command<MainCommandReceiver>) -> Unit) {
    val event = lifecycleEvent()
    LaunchedEffect(event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            onExecuteCommand(MainCommand.VerifySession)
        }
    }
}

@Composable
private fun DialogsHandle(
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
        MainScreen(
            uiState = MainUiState(),
            uiEvent = null
        ) {}
    }
}