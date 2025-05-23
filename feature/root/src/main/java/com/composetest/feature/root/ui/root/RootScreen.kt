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
import androidx.navigation.NavHostController
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
import com.composetest.core.router.extensions.currentRouteChangesFlow
import com.composetest.core.router.extensions.getResultFlow
import com.composetest.core.router.extensions.navigateTo
import com.composetest.core.router.results.account.AccountUpdateResult
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.utils.UiEventsObserver
import com.composetest.feature.root.R
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.models.BottomFeatureNavigationModel
import com.composetest.feature.root.navigation.NavGraphs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import com.composetest.core.designsystem.R as DesignSystemResources

@Composable
internal fun RootScreen(
    uiState: RootUiState,
    uiEvent: Flow<RootUiEvent> = emptyFlow(),
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit = {},
    mainNavController: NavHostController = rememberNavController(),
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = getModalDrawerContent(uiState, onExecuteCommand, drawerState),
    ) {
        ScreenScaffold(
            topBar = getTopBar(drawerState),
            bottomBar = getBottomBar(uiState, onExecuteCommand)
        ) {
            Navigation(
                drawerState = drawerState,
                uiState = uiState,
                uiEvent = uiEvent,
                mainNavController = mainNavController,
                onExecuteCommand = onExecuteCommand
            )
        }
    }
}

@Composable
private fun Navigation(
    drawerState: DrawerState,
    uiState: RootUiState,
    uiEvent: Flow<RootUiEvent>,
    mainNavController: NavHostController,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    if (uiState.firstDestination == null) return
    val navController = rememberNavController()
    val navGraphs by NavGraphs(mainNavController)
    NavHost(navController = navController, startDestination = uiState.firstDestination) {
        navGraphs.forEach { it() }
    }
    UiEventsHandler(
        uiEvent = uiEvent,
        rootNavController = navController,
        mainNavController = mainNavController
    )
    LaunchedEffectHandler(
        onExecuteCommand = onExecuteCommand,
        rootNavController = navController,
        mainNavController = mainNavController
    )
    BackHandler(drawerState = drawerState, onExecuteCommand = onExecuteCommand)
}

private fun getModalDrawerContent(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit,
    drawerState: DrawerState,
) = @Composable {
    ModalDrawerSheet {
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.twenty)) {
            ModalDrawerHeader(uiState = uiState, onExecuteCommand = onExecuteCommand)
            HorizontalDivider()
            ModalDrawerItems(
                uiState = uiState,
                onExecuteCommand = onExecuteCommand,
                drawerState = drawerState
            )
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
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit,
    drawerState: DrawerState,
) {
    val coroutineScope = rememberCoroutineScope()
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
                    coroutineScope.launch { drawerState.close() }
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
        TextButton(onClick = { onExecuteCommand(RootCommand.Logout) }) {
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
        uiState.bottomNavigationFeatures.forEach { bottomNavigation ->
            val label = bottomNavigation.feature.textId?.let { stringResource(it) }.orEmpty()
            NavigationBarItem(
                selected = bottomNavigation.selected,
                onClick = { onExecuteCommand(RootCommand.NavigateToFeature(bottomNavigation.feature)) },
                label = {
                    Text(text = label, style = MaterialTheme.typography.labelLarge)
                },
                icon = {
                    Icon(
                        painter = painterResource(bottomNavigation.feature.iconId),
                        contentDescription = label
                    )
                }
            )
        }
    }
}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<RootUiEvent>,
    rootNavController: NavHostController,
    mainNavController: NavHostController,
) {
    val activity = LocalActivity.current
    UiEventsObserver(uiEvent) {
        when (it) {
            is RootUiEvent.FinishApp -> activity?.finish()
            is RootUiEvent.NavigateToFeature -> mainNavController.navigateTo(it.navigationModel)
            is RootUiEvent.NavigateToBottomFeature -> rootNavController.navigateTo(it.navigationModel)
        }
    }
}

@Composable
private fun LaunchedEffectHandler(
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit,
    rootNavController: NavHostController,
    mainNavController: NavHostController,
) {
    LaunchedEffect(Unit) {
        mainNavController.getResultFlow(AccountUpdateResult::class).collect {
            onExecuteCommand(RootCommand.UpdateUserData)
        }
    }
    LaunchedEffect(Unit) {
        rootNavController.currentRouteChangesFlow.collect {
            onExecuteCommand(RootCommand.CurrentScreenObservable(it))
        }
    }
}

@Composable
private fun BackHandler(
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
        )
    }
}