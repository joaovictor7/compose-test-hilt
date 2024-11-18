package com.composetest.feature.root.ui.root

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.extensions.asActivity
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.home.navigation.homeRootNavGraph
import com.composetest.feature.root.enums.NavigationLocal
import kotlinx.coroutines.launch
import com.composetest.core.designsystem.R as DesignSystemResources

internal object RootScreen : Screen<RootUiState, RootCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: RootUiState,
        onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
    ) {
        val context = LocalContext.current
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = getModalDrawerContent(uiState, onExecuteCommand),
        ) {
            Scaffold(
                topBar = getTopBar(drawerState),
                bottomBar = getBottomBar(uiState, onExecuteCommand)
            ) { paddingValues ->
                Navigation(
                    modifier = Modifier.padding(paddingValues),
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
    }
}

private fun getModalDrawerContent(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) = @Composable {
    ModalDrawerSheet {
        Row(
            modifier = Modifier
                .padding(horizontal = spacings.twelve)
                .windowInsetsPadding(WindowInsets.statusBars),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacings.twelve)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(DesignSystemResources.drawable.ic_person_off),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(verticalArrangement = Arrangement.spacedBy(spacings.four)) {
                uiState.userModalDrawerModel.username?.let {
                    Text(
                        text = uiState.userModalDrawerModel.username,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = uiState.userModalDrawerModel.email,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        HorizontalDivider()
        ModalDrawerItems(uiState = uiState, onExecuteCommand = onExecuteCommand)
    }
}

@Composable
private fun ModalDrawerItems(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(spacings.eight)) {
        items(uiState.modalDrawerFeatures) {
            val label = stringResource(it.textId)
            NavigationDrawerItem(
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(it.iconId),
                        contentDescription = label
                    )
                },
                selected = it.selected,
                onClick = {
                    onExecuteCommand(
                        RootCommand.SetSelectedBottomNavigationFeature(
                            it,
                            NavigationLocal.MODAL_DRAWER
                        )
                    )
                }
            )
        }
    }
}

private fun getTopBar(drawerState: DrawerState) = @Composable {
    val coroutineScope = rememberCoroutineScope()
    Row(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        IconButton(
            onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }
        ) {
            Icon(
                painter = painterResource(DesignSystemResources.drawable.ic_menu),
                contentDescription = null
            )
        }
    }
}

private fun getBottomBar(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) = @Composable {
    NavigationBar {
        uiState.bottomFeatures.forEach {
            val label = stringResource(it.textId)
            NavigationBarItem(
                selected = it.selected,
                onClick = {
                    onExecuteCommand(
                        RootCommand.SetSelectedBottomNavigationFeature(it, NavigationLocal.BOTTOM)
                    )
                },
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