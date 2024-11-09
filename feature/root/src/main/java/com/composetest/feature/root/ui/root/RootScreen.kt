package com.composetest.feature.root.ui.root

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.R
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
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val context = LocalContext.current
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    ModalDrawerHeader(uiState = uiState, onExecuteCommand = onExecuteCommand)
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = { Text(text = "Drawer Item") },
                        selected = true,
                        onClick = { /*TODO*/ }
                    )
                }
            },
        ) {
            Scaffold(bottomBar = getBottomBar(onExecuteCommand, uiState)) { paddingValues ->
                Navigation(
                    modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                    onExecuteCommand = onExecuteCommand
                )
                BackHandler {
                    onExecuteCommand(RootCommand.BackHandler)
                }
            }
        }
        LaunchedEffect(uiState.finishApp) {
            if (uiState.finishApp) context.asActivity?.finish()
        }
    }
}

@Composable
private fun ModalDrawerHeader(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.ic_person_off),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
//        Text(
//            text = uiState.finishApp,
//            modifier = Modifier.padding(16.dp),
//            style = MaterialTheme.typography.bodyMedium
//        )
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