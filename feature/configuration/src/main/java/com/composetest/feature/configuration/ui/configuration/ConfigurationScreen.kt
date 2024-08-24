package com.composetest.feature.configuration.ui.configuration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.enums.Configuration

internal object ConfigurationScreen : Screen<ConfigurationUiState, ConfigurationCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ConfigurationUiState,
        onExecuteCommand: (Command<ConfigurationCommandReceiver>) -> Unit
    ) {
        Column(modifier = Modifier.padding(spacings.eighteen)) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = spacings.sixteen,
                horizontalArrangement = Arrangement.spacedBy(spacings.sixteen)
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
                .fillMaxSize()
                .padding(spacings.eighteen)
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
            )
        ) { }
    }
}