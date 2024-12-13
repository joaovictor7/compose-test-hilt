package com.composetest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.utils.lifecycleEvent
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.configuration.navigation.configurationNavGraph
import com.composetest.feature.home.navigation.homeNavGraph
import com.composetest.feature.login.navigation.loginNavGraph
import com.composetest.feature.profile.navigation.profileNavGraph
import com.composetest.feature.root.navigation.rootNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSplashScreen()
        observerUiState()
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LifecycleHandler(viewModel::executeCommand)
            ComposeTestTheme(
                dynamicColor = uiState.appTheme.dynamicColors,
                theme = uiState.appTheme.theme
            ) {
                HandleDialogs(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
                Navigation(
                    firstScreenDestination = LoginDestination::class,
                    onExecuteCommand = viewModel::executeCommand
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.executeCommand(MainCommand.FetchRemoteConfig)
    }

    private fun observerUiState() = lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { uiState ->
            setEdgeToEdge(uiState)
        }
    }

    private fun setEdgeToEdge(uiState: MainUiState) {
        enableEdgeToEdge(uiState.statusBarStyle, uiState.navigationBarStyle)
        if (uiState.forceNavigationBarTransparency) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    private fun setSplashScreen() {
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.uiState.value.showSplashScreen
        }
    }
}

@Composable
private fun Navigation(
    firstScreenDestination: KClass<*>,
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
    }
}

@Composable
private fun LifecycleHandler(onExecuteCommand: (Command<MainCommandReceiver>) -> Unit) {
    val event = lifecycleEvent()
    LaunchedEffect(event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            onExecuteCommand(MainCommand.VerifySession)
        }
    }
}

@Composable
private fun HandleDialogs(
    uiState: MainUiState,
    onExecuteCommand: (Command<MainCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteCommand(MainCommand.DismissAlertDialog)
    }
}