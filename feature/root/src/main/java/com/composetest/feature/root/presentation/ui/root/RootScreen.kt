package com.composetest.feature.root.presentation.ui.root

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
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.composetest.core.designsystem.component.scaffold.ScreenScaffold
import com.composetest.core.designsystem.component.topbar.TopBarWithoutTitle
import com.composetest.core.designsystem.component.topbar.enums.TopBarAction
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.extension.horizontalScreenMargin
import com.composetest.core.designsystem.extension.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.root.navigation.navstack.LocalRootNavBackStack
import com.composetest.core.router.extension.navigateTo
import com.composetest.core.router.navkey.home.HomeNavKey
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.feature.root.R
import com.composetest.feature.root.presentation.enums.NavigationFeature
import com.composetest.feature.root.presentation.model.BottomFeatureNavigationModel
import com.composetest.feature.root.presentation.ui.root.viewmodel.RootIntent
import com.composetest.feature.root.presentation.ui.root.viewmodel.RootIntentReceiver
import com.composetest.feature.root.presentation.ui.root.viewmodel.RootUiEvent
import com.composetest.feature.root.presentation.ui.root.viewmodel.RootUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import com.composetest.core.designsystem.R as DesignSystemResources

@Composable
internal fun RootScreen(
    uiState: RootUiState,
    uiEvent: Flow<RootUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit = {},
    mainNavBackStack: NavBackStack<NavKey> = rememberNavBackStack(),
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = getModalDrawerContent(uiState, onExecuteIntent, drawerState),
    ) {
        ScreenScaffold(
            topBar = getTopBar(drawerState),
            bottomBar = getBottomBar(uiState, onExecuteIntent),
        ) {
            Navigation(
                drawerState = drawerState,
                uiState = uiState,
                uiEvent = uiEvent,
                mainNavBackStack = mainNavBackStack,
                onExecuteIntent = onExecuteIntent,
            )
        }
    }
}

@Composable
private fun Navigation(
    drawerState: DrawerState,
    uiState: RootUiState,
    uiEvent: Flow<RootUiEvent>,
    mainNavBackStack: NavBackStack<NavKey>,
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit
) {
    if (uiState.firstNavKey == null) return
    val rootBackStack = rememberNavBackStack(uiState.firstNavKey)
    CompositionLocalProvider(LocalRootNavBackStack provides rootBackStack) {
        NavDisplay(
            backStack = rootBackStack,
            entryProvider = entryProvider {
                uiState.navGraphs.forEach { navGraph ->
                    navGraph.run { registerEntries() }
                }
            }
        )
    }
    UiEventsHandler(
        uiEvent = uiEvent,
        mainNavBackStack = mainNavBackStack,
        rootBackStack = rootBackStack,
    )
    LaunchedEffectHandler(
        onExecuteIntent = onExecuteIntent,
        rootBackStack = rootBackStack,
    )
    BackHandler(drawerState = drawerState, onExecuteIntent = onExecuteIntent)
}

private fun getModalDrawerContent(
    uiState: RootUiState,
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit,
    drawerState: DrawerState,
) = @Composable {
    ModalDrawerSheet {
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.large)) {
            ModalDrawerHeader(uiState = uiState, onExecuteIntent = onExecuteIntent)
            HorizontalDivider()
            ModalDrawerItems(
                uiState = uiState,
                onExecuteIntent = onExecuteIntent,
                drawerState = drawerState
            )
            Spacer(Modifier.weight(1f))
            LogoutButton(onExecuteIntent = onExecuteIntent)
        }
    }
}

@Composable
private fun ModalDrawerHeader(
    uiState: RootUiState,
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit
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
            horizontalArrangement = Arrangement.spacedBy(Spacing.medium),
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
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.tiny)) {
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
                onClick = { onExecuteIntent(RootIntent.NavigateToFeature(NavigationFeature.PROFILE)) },
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
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit,
    drawerState: DrawerState,
) {
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(Spacing.tiny)) {
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
                    onExecuteIntent(RootIntent.NavigateToFeature(item))
                }
            )
        }
    }
}

@Composable
private fun LogoutButton(onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit) {
    Row(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .screenMargin(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
    ) {
        TextButton(onClick = { onExecuteIntent(RootIntent.Logout) }) {
            Icon(
                painter = painterResource(DesignSystemResources.drawable.ic_logout_medium),
                contentDescription = null
            )
            Spacer(Modifier.width(Spacing.medium))
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
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit
) = @Composable {
    NavigationBar {
        uiState.bottomNavigationFeatures.forEach { bottomNavigation ->
            val label = bottomNavigation.feature.textId?.let { stringResource(it) }.orEmpty()
            NavigationBarItem(
                selected = bottomNavigation.selected,
                onClick = { onExecuteIntent(RootIntent.NavigateToFeature(bottomNavigation.feature)) },
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
    mainNavBackStack: NavBackStack<NavKey>,
    rootBackStack: NavBackStack<NavKey>,
) {
    val activity = LocalActivity.current
    UiEventsObserver(uiEvent) {
        when (it) {
            is RootUiEvent.FinishApp -> activity?.finish()
            is RootUiEvent.NavigateToFeature -> mainNavBackStack.navigateTo(it.navigationModel)
            is RootUiEvent.NavigateToBottomFeature -> rootBackStack.navigateTo(it.navigationModel)
        }
    }
}

@Composable
private fun LaunchedEffectHandler(
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit,
    rootBackStack: NavBackStack<NavKey>,
) {
    LaunchedEffect(rootBackStack.lastOrNull()) {
        onExecuteIntent(RootIntent.CurrentScreenObservable(rootBackStack.lastOrNull()))
    }
}

@Composable
private fun BackHandler(
    drawerState: DrawerState,
    onExecuteIntent: (Intent<RootIntentReceiver>) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    BackHandler {
        if (drawerState.isOpen) {
            coroutineScope.launch { drawerState.close() }
        } else {
            onExecuteIntent(RootIntent.BackHandler)
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        RootScreen(
            uiState = RootUiState(
                firstNavKey = HomeNavKey,
                bottomNavigationFeatures = listOf(
                    BottomFeatureNavigationModel(NavigationFeature.HOME, true),
                    BottomFeatureNavigationModel(NavigationFeature.HOME)
                )
            )
        )
    }
}
