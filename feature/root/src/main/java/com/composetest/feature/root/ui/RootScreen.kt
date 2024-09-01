package com.composetest.feature.root.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.animations.SlideInOutAnimation
import com.composetest.core.designsystem.components.dock.IconDock
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.extensions.asActivity
import com.composetest.core.router.destinations.home.HomeDestination
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
        val context = LocalContext.current
        Scaffold { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                Navigation(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = spacings.eighteen)
                        .padding(top = spacings.eighteen),
                    firstScreenDestination = HomeDestination::class,
                    onExecuteCommand = onExecuteCommand
                )
                Dock(
                    uiState = uiState,
                    onExecuteCommand = onExecuteCommand
                )
            }
            BackHandler {
                onExecuteCommand(RootCommand.BackHandler)
            }
        }
        LaunchedEffect(uiState.finishApp) {
            if (uiState.finishApp) context.asActivity?.finish()
        }
    }
}

@Composable
private fun Navigation(
    modifier: Modifier,
    firstScreenDestination: KClass<*>,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    val navController = rememberNavController()
    onExecuteCommand(RootCommand.SetRootNavGraph(navController))
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = firstScreenDestination
    ) {
        homeRootNavGraph()
        configurationRootNavGraph()
    }
}

@Composable
private fun BoxScope.Dock(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    SlideInOutAnimation(
        modifier = Modifier.align(Alignment.BottomCenter),
        visible = uiState.dockVisible
    ) {
        IconDock(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .navigationBarsPadding()
                .padding(bottom = spacings.ten)
                .height(components.dockHeight),
            shape = MaterialTheme.shapes.extraLarge,
            selectedIndex = uiState.selectedDockItem,
            dockItems = uiState.dockItems
        ) {
            onExecuteCommand(RootCommand.ChangeSelectedDockItem(it))
        }
    }
//    Row(
//        modifier = Modifier.align(Alignment.BottomCenter),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        SlideInOutAnimation(visible = uiState.dockVisible) {
//            IconDock(
//                modifier = Modifier
//                    .fillMaxWidth(0.5f)
//                    .navigationBarsPadding()
//                    .padding(bottom = spacings.ten)
//                    .height(components.dockHeight),
//                shape = MaterialTheme.shapes.extraLarge,
//                selectedIndex = uiState.selectedDockItem,
//                dockItems = uiState.dockItems
//            ) {
//                onExecuteCommand(RootCommand.ChangeSelectedDockItem(it))
//            }
//        }
//    }
}