package com.composetest.feature.configuration.ui.configuration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.R
import com.composetest.feature.configuration.enums.Configuration
import kotlinx.coroutines.flow.Flow

private const val LIMIT_CONFIGURATIONS_PER_LINE = 2

internal object ConfigurationScreen :
    Screen<ConfigurationUiState, ConfigurationUiEvent, ConfigurationCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ConfigurationUiState,
        uiEvent: Flow<ConfigurationUiEvent>?,
        onExecuteCommand: (Command<ConfigurationCommandReceiver>) -> Unit
    ) {
        ScreenScaffold(
            topBar = { LeftTopBar(titleId = R.string.configuration_title) }
        ) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.screenMargin(),
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
            }
        }
    }
}

@Composable
private fun ConfigurationCard(
    onExecuteCommand: (Command<ConfigurationCommandReceiver>) -> Unit,
    configuration: Configuration
) {
    OutlinedCard(
        onClick = { onExecuteCommand(ConfigurationCommand.ClickConfiguration(configuration)) }
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
@Preview
private fun Preview() {
    ComposeTestTheme {
        ConfigurationScreen(
            uiState = ConfigurationUiState(
                configurations = Configuration.entries
            ),
            null
        ) { }
    }
}