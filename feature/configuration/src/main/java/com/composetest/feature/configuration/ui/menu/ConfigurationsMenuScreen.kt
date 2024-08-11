package com.composetest.feature.configuration.ui.menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen

internal object ConfigurationsMenuScreen :
    Screen<ConfigurationsMenuUiState, ConfigurationsMenuCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ConfigurationsMenuUiState,
        onExecuteCommand: (Command<ConfigurationsMenuCommandReceiver>) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        ConfigurationsMenuScreen(uiState = ConfigurationsMenuUiState()) { }
    }
}