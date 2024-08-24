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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.dock.IconDock
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.router.destinations.home.HomeRootDestination
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.navigation.configurationRootNavGraph
import com.composetest.feature.home.navigation.homeRootNavGraph
import com.composetest.feature.root.dimensions.components
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
                    .padding(spacings.eighteen)
            ) {
                Navigation(
                    firstScreenDestination = HomeRootDestination::class,
                    onExecuteCommand = onExecuteCommand
                )
                IconDock(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(0.50f)
                        .height(components.dockHeight),
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
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    val navController = rememberNavController()
    onExecuteCommand(RootCommand.SetRootNavGraph(navController))
    NavHost(
        navController = navController,
        startDestination = firstScreenDestination
    ) {
        homeRootNavGraph()
        configurationRootNavGraph()
    }
}