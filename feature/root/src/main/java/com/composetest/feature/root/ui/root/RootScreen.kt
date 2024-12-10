package com.composetest.feature.root.ui.root

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.toolbar.Toolbar
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.enums.toolbar.ToolbarAction
import com.composetest.core.designsystem.enums.toolbar.ToolbarType
import com.composetest.core.designsystem.extensions.asActivity
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.params.toolbar.ToolbarActionParam
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.home.navigation.homeRootNavGraph
import com.composetest.feature.root.dimensions.components
import com.composetest.feature.root.enums.NavigationFeature
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
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val screenTitle = uiState.currentScreenTitle
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = getModalDrawerContent(uiState, onExecuteCommand),
        ) {
            if (screenTitle == null) return@ModalNavigationDrawer
            Toolbar(
                type = ToolbarType.CENTRALIZED,
                titleId = screenTitle,
                bottomBar = getBottomBar(uiState, onExecuteCommand),
                navigationAction = ToolbarActionParam(ToolbarAction.MENU) {
                    coroutineScope.launch { drawerState.open() }
                }
            ) {
                Navigation(
                    uiState = uiState,
                    onExecuteCommand = onExecuteCommand
                )
                BackHandler {
                    if (drawerState.isOpen) {
                        coroutineScope.launch { drawerState.close() }
                    } else {
                        onExecuteCommand(RootCommand.BackHandler)
                    }
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
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) {
    if (uiState.firstDestination == null) return
    val navController = rememberNavController()
    onExecuteCommand(RootCommand.SetRootNavGraph(navController))
    NavHost(
        navController = navController,
        startDestination = uiState.firstDestination
    ) {
        homeRootNavGraph()
    }
}

private fun getModalDrawerContent(
    uiState: RootUiState,
    onExecuteCommand: (Command<RootCommandReceiver>) -> Unit
) = @Composable {
    ModalDrawerSheet {
        Column(verticalArrangement = Arrangement.spacedBy(spacings.twenty)) {
            ModalDrawerHeader(uiState = uiState, onExecuteCommand = onExecuteCommand)
            HorizontalDivider()
            ModalDrawerItems(uiState = uiState, onExecuteCommand = onExecuteCommand)
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
            horizontalArrangement = Arrangement.spacedBy(spacings.twelve)
        ) {
            Image(
                modifier = Modifier
                    .size(components.modelDrawerUserImageSize)
                    .clip(CircleShape),
                painter = painterResource(DesignSystemResources.drawable.ic_person_off),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
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
        if (uiState.showEditProfile) {
            IconButton(onClick = { onExecuteCommand(RootCommand.NavigateToFeature(NavigationFeature.PROFILE)) }) {
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
    LazyColumn(verticalArrangement = Arrangement.spacedBy(spacings.four)) {
        items(uiState.modalDrawerNavigationFeaturesToList) {
            val label = it.textId?.let { stringResource(it) }.orEmpty()
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
                        painter = painterResource(it.iconId),
                        contentDescription = label
                    )
                },
                onClick = {
                    onExecuteCommand(RootCommand.NavigateToFeature(it))
                }
            )
        }
    }
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