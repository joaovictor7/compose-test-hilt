package com.composetest.feature.root.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.dock.IconDock
import com.composetest.core.designsystem.dimensions.components
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.destinations.home.HomeRootDestination
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.navigation.configurationNavGraph
import com.composetest.feature.home.navigation.homeNavGraph
import kotlin.reflect.KClass

internal object RootScreen : Screen<RootUiState, RootCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: RootUiState,
        onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
    ) {
        Scaffold { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Navigation(
                    firstScreenDestination = HomeRootDestination::class,
                    uiState = uiState
                )
                IconDock(
                    modifier = Modifier
                        .padding(bottom = spacings.eighteen)
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(0.35f)
                        .height(components.rootDockHeight),
                    shape = MaterialTheme.shapes.extraLarge,
                    selectedIndex = uiState.selectedDockItem,
                    dockItems = uiState.dockItems
                ) {
                    onExecuteCommand(RootCommand.ChangeDockItemSelected(it))
                }
            }
        }
    }
}

@Composable
private fun Navigation(
    firstScreenDestination: KClass<*>,
    uiState: RootUiState
) {
//    val navHostController = rememberNavController()
//    onExecuteCommand(RootCommand.SetRootNavGraph(navHostController))
    uiState.navHostController?.let {
        NavHost(
            navController = it,
            startDestination = firstScreenDestination
        ) {
            homeNavGraph()
            configurationNavGraph()
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        RootScreen(uiState = RootUiState()) { }
    }
}