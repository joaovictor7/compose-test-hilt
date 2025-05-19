package com.composetest.feature.configuration.presenter.ui.configuration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.extensions.navigateTo
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.utils.UiEventsObserver
import com.composetest.feature.configuration.presenter.enums.Configuration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

private const val LIMIT_CONFIGURATIONS_PER_LINE = 2

@Composable
internal fun ConfigurationScreen(
    uiState: ConfigurationUiState,
    uiEvent: Flow<ConfigurationUiEvent> = emptyFlow(),
    mainNavController: NavHostController = rememberNavController(),
    onExecuteCommand: (Command<ConfigurationCommandReceiver>) -> Unit = {}
) {
    UiEventsHandler(uiEvent = uiEvent, mainNavController = mainNavController)
    LazyVerticalStaggeredGrid(
        modifier = Modifier.horizontalScreenMargin(),
        columns = StaggeredGridCells.Fixed(LIMIT_CONFIGURATIONS_PER_LINE),
        verticalItemSpacing = Spacing.sixteen,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sixteen),
    ) {
        items(uiState.configurations) { item ->
            ConfigurationCard(
                onExecuteCommand = onExecuteCommand,
                configuration = item
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(bottom = screenMargin)
            )
        }
    }
}

@Composable
private fun ConfigurationCard(
    onExecuteCommand: (Command<ConfigurationCommandReceiver>) -> Unit,
    configuration: Configuration
) {
    OutlinedCard(
        onClick = { onExecuteCommand(ConfigurationCommand.ConfigurationClick(configuration)) }
    ) {
        Box(
            modifier = Modifier
                .padding(Spacing.twenty)
                .fillMaxSize()
        ) {
            Icon(painter = painterResource(configuration.iconId), contentDescription = null)
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(configuration.textId),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<ConfigurationUiEvent>,
    mainNavController: NavHostController
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is ConfigurationUiEvent.NavigateTo -> mainNavController.navigateTo(it.navigateModel)
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        ConfigurationScreen(
            uiState = ConfigurationUiState(
                configurations = Configuration.entries
            ),
        )
    }
}