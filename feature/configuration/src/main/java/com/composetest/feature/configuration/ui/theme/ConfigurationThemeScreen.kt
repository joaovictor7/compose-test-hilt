package com.composetest.feature.configuration.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.components.switches.ThumbSwitch
import com.composetest.core.designsystem.components.toolbar.Toolbar
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.extensions.opacity
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.params.switches.SwitchType
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.dimensions.components
import com.composetest.feature.configuration.enums.ThemeConfiguration
import kotlinx.coroutines.flow.Flow
import com.composetest.feature.configuration.R as ConfigurationResources

internal object ConfigurationThemeScreen :
    Screen<ConfigurationThemeUiState, ConfigurationThemeUiEvent, ConfigurationThemeCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ConfigurationThemeUiState,
        uiEvent: Flow<ConfigurationThemeUiEvent>?,
        onExecuteCommand: (Command<ConfigurationThemeCommandReceiver>) -> Unit
    ) {
        Toolbar(
            modifier = Modifier.screenMargin(),
            titleId = ConfigurationResources.string.configuration_theme_text
        ) {
            Section(titleId = ConfigurationResources.string.configuration_theme_mode_title) {
                Theme(uiState = uiState, onExecuteCommand = onExecuteCommand)
            }
            Section(titleId = ConfigurationResources.string.configuration_theme_colors_title) {
                DynamicColor(uiState = uiState, onExecuteCommand = onExecuteCommand)
            }
        }
    }
}

@Composable
private fun Section(
    @StringRes titleId: Int,
    content: @Composable () -> Unit
) {
    Text(
        text = stringResource(titleId),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(Modifier.height(spacings.sixteen))
    content()
    Spacer(Modifier.height(spacings.twentyEight))
}

@Composable
private fun Theme(
    uiState: ConfigurationThemeUiState,
    onExecuteCommand: (Command<ConfigurationThemeCommandReceiver>) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        uiState.themes.forEach { theme ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = spacings.four)
                    .clip(MaterialTheme.shapes.medium)
                    .setSelectedBackgroundColor(selectedTheme = theme == uiState.selectedTheme)
                    .size(components.themeButtonSize)
                    .clickable {
                        onExecuteCommand(ConfigurationThemeCommand.ChangeTheme(theme))
                    }
            ) {
                Icon(painter = painterResource(theme.iconId), contentDescription = null)
                Spacer(Modifier.height(spacings.eight))
                Text(
                    text = stringResource(theme.textId),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun DynamicColor(
    uiState: ConfigurationThemeUiState,
    onExecuteCommand: (Command<ConfigurationThemeCommandReceiver>) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(ConfigurationResources.string.configuration_theme_dynamic_colors),
            style = MaterialTheme.typography.bodyMedium
        )
        ThumbSwitch(
            checked = uiState.dynamicColors,
            type = SwitchType.CHECK,
            onCheckedChange = { onExecuteCommand(ConfigurationThemeCommand.ChangeDynamicColor(it)) }
        )
    }
}

@Composable
private fun Modifier.setSelectedBackgroundColor(selectedTheme: Boolean) = also {
    if (selectedTheme) {
        return background(color = MaterialTheme.colorScheme.primary.opacity(0.12f))
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        ConfigurationThemeScreen(
            uiState = ConfigurationThemeUiState(
                themes = ThemeConfiguration.entries,
                selectedTheme = ThemeConfiguration.AUTO,
                dynamicColors = true
            ),
            uiEvent = null
        ) { }
    }
}