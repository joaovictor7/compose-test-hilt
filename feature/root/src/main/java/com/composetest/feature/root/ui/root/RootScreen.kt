package com.composetest.feature.root.ui.root

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.topbar.TopBarWithoutTitle
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.root.R
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.models.BottomFeatureNavigationModel
import com.composetest.feature.root.navigation.navGraphs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.composetest.core.designsystem.R as DesignSystemResources

internal object RootScreen : Screen<RootUiState, RootUiEvent, RootCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: RootUiState,
        uiEvent: Flow<RootUiEvent>?,
        onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
    ) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        LaunchedEffectsHandler(uiEvent = uiEvent, drawerState = drawerState)
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = getModalDrawerContent(uiState, onExecuteCommand),
        ) {
            ScreenScaffold(
                topBar = getTopBar(drawerState),
                bottomBar = getBottomBar(uiState, onExecuteCommand)
            ) {
                Navigation(
                    drawerState = drawerState,
                    uiState = uiState,
                    onExecuteCommand = onExecuteCommand
                )
            }
        }
    }
}

@Composable
private fun Navigation(
    drawerState: DrawerState,
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    if (uiState.firstDestination == null) return
    val navController = rememberNavController()
    onExecuteCommand(RootCommand.SetRootNavGraph(navController))
    BackHandlers(drawerState = drawerState, onExecuteCommand = onExecuteCommand)
    NavHost(navController = navController, startDestination = uiState.firstDestination) {
        navGraphs.forEach {
            it.run { navGraph(false) }
        }
    }
}

private fun getModalDrawerContent(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) = @Composable {
    ModalDrawerSheet {
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.twenty)) {
            ModalDrawerHeader(uiState = uiState, onExecuteCommand = onExecuteCommand)
            HorizontalDivider()
            ModalDrawerItems(uiState = uiState, onExecuteCommand = onExecuteCommand)
            Spacer(Modifier.weight(1f))
            LogoutButton(onExecuteCommand = onExecuteCommand)
        }
    }
}

@Composable
private fun ModalDrawerHeader(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    Row(
        modifier = Modifier
            .horizontalScreenMargin()
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.twelve),
            modifier = Modifier.weight(0.85f)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(DesignSystemResources.drawable.ic_person_off),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.four)) {
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
        if (uiState.showEditProfile) {
            IconButton(
                onClick = { onExecuteCommand(RootCommand.NavigateToFeature(NavigationFeature.PROFILE)) },
                modifier = Modifier.weight(0.15f)
            ) {
                Icon(
                    painter = painterResource(DesignSystemResources.drawable.ic_edit_medium),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun ModalDrawerItems(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(Spacing.four)) {
        items(uiState.modalDrawerNavigationFeaturesToList) { item ->
            val label = item.textId?.let { stringResource(it) }.orEmpty()
            NavigationDrawerItem(
                selected = false,
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = label
                    )
                },
                onClick = {
                    onExecuteCommand(RootCommand.NavigateToFeature(item))
                }
            )
        }
    }
}

@Composable
private fun LogoutButton(onExecuteCommand: (Command<RootCommandReceiver>) -> Unit) {
    Row(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .screenMargin(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.twelve)
    ) {
        TextButton(
            onClick = { onExecuteCommand(RootCommand.Logout) }
        ) {
            Icon(
                painter = painterResource(DesignSystemResources.drawable.ic_logout_medium),
                contentDescription = null
            )
            Spacer(Modifier.width(Spacing.twelve))
            Text(
                text = stringResource(R.string.logout),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun getTopBar(modalDrawerState: DrawerState) = @Composable {
    val coroutineScope = rememberCoroutineScope()
    TopBarWithoutTitle(
        navigationAction = TopBarAction.MENU,
        onClickNavigationAction = {
            coroutineScope.launch { modalDrawerState.open() }
        }
    )
}

private fun getBottomBar(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) = @Composable {
    NavigationBar {
        uiState.bottomNavigationFeatures.forEach {
            val label = it.feature.textId?.let { stringResource(it) }.orEmpty()
            NavigationBarItem(
                selected = it.selected,
                onClick = {
                    onExecuteCommand(RootCommand.NavigateToFeature(it.feature))
                },
                label = {
                    Text(text = label, style = MaterialTheme.typography.labelLarge)
                },
                icon = {
                    Icon(
                        painter = painterResource(it.feature.iconId),
                        contentDescription = label
                    )
                }
            )
        }
    }
}

@Composable
private fun LaunchedEffectsHandler(uiEvent: Flow<RootUiEvent>?, drawerState: DrawerState) {
    val activity = LocalActivity.current
    LaunchedEffect(Unit) {
        uiEvent?.collect {
            when (it) {
                is RootUiEvent.FinishApp -> activity?.finish()
                is RootUiEvent.CloseModalDrawer -> drawerState.close()
            }
        }
    }
}

@Composable
private fun BackHandlers(
    drawerState: DrawerState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    BackHandler {
        if (drawerState.isOpen) {
            coroutineScope.launch { drawerState.close() }
        } else {
            onExecuteCommand(RootCommand.BackHandler)
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        RootScreen(
            uiState = RootUiState(
                firstDestination = HomeDestination,
                bottomNavigationFeatures = listOf(
                    BottomFeatureNavigationModel(NavigationFeature.HOME, true),
                    BottomFeatureNavigationModel(NavigationFeature.HOME)
                )
            ),
            uiEvent = null
        ) { }
    }
}