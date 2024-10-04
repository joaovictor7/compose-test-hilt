package com.composetest.feature.root.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.extensions.asActivity
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.navigation.configurationRootNavGraph
import com.composetest.feature.home.navigation.homeRootNavGraph
import com.composetest.feature.profile.navigation.profileRootNavGraph
import com.composetest.feature.root.enums.NavigationBottomBar

internal object RootScreen : Screen<RootUiState, RootCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: RootUiState,
        onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
    ) {
        val context = LocalContext.current
        Scaffold(bottomBar = getBottomBar(onExecuteCommand, uiState)) { paddingValues ->
            Navigation(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                onExecuteCommand = onExecuteCommand
            )
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
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    val navController = rememberNavController()
    onExecuteCommand(RootCommand.SetRootNavGraph(navController))
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeDestination
    ) {
        homeRootNavGraph()
        profileRootNavGraph()
        configurationRootNavGraph()
    }
}

private fun getBottomBar(
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit,
    uiState: RootUiState
) = @Composable {
    NavigationBar {
        NavigationBottomBar.entries.forEach {
            val label = stringResource(it.labelId)
            NavigationBarItem(
                selected = it == uiState.selectedBottomBarItem,
                onClick = { onExecuteCommand(RootCommand.SetSelectedNavigationBottomBar(it)) },
                label = {
                    Text(text = label, style = MaterialTheme.typography.labelLarge)
                },
                icon = {
                    Icon(
                        painter = painterResource(it.iconId),
                        contentDescription = label
                    )
                }
            )
        }
    }
}