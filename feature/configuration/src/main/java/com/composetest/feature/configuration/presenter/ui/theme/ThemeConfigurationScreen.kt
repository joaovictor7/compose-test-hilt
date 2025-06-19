package com.composetest.feature.configuration.presenter.ui.theme

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
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.component.scaffold.ScreenScaffold
import com.composetest.core.designsystem.component.switch.ThumbSwitch
import com.composetest.core.designsystem.component.topbar.LeftTopBar
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.enums.switchs.SwitchType
import com.composetest.core.designsystem.extension.opacity
import com.composetest.core.designsystem.extension.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.configuration.presenter.enums.ThemeConfiguration
import com.composetest.feature.configuration.R as ConfigurationResources

@Composable
internal fun ThemeConfigurationScreen(
    uiState: ThemeConfigurationUiState,
    onExecuteIntent: (Intent<ThemeConfigurationIntentReceiver>) -> Unit = {}
) {
    ScreenScaffold(
        modifier = Modifier.screenMargin(),
        topBar = { LeftTopBar(titleId = ConfigurationResources.string.configuration_theme_text) }
    ) {
        Section(titleId = ConfigurationResources.string.configuration_theme_mode_title) {
            Theme(uiState = uiState, onExecuteIntent = onExecuteIntent)
        }
        Section(titleId = ConfigurationResources.string.configuration_theme_colors_title) {
            DynamicColor(uiState = uiState, onExecuteIntent = onExecuteIntent)
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
    Spacer(Modifier.height(Spacing.semiLarge))
    content()
    Spacer(Modifier.height(Spacing.xxLarge))
}

@Composable
private fun Theme(
    uiState: ThemeConfigurationUiState,
    onExecuteIntent: (Intent<ThemeConfigurationIntentReceiver>) -> Unit
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
                    .padding(horizontal = Spacing.tiny)
                    .clip(MaterialTheme.shapes.medium)
                    .setSelectedBackgroundColor(selectedTheme = theme == uiState.selectedTheme)
                    .size(100.dp)
                    .clickable {
                        onExecuteIntent(ThemeConfigurationIntent.ChangeThemeConfiguration(theme))
                    }
            ) {
                Icon(painter = painterResource(theme.iconId), contentDescription = null)
                Spacer(Modifier.height(Spacing.small))
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
    uiState: ThemeConfigurationUiState,
    onExecuteIntent: (Intent<ThemeConfigurationIntentReceiver>) -> Unit
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
            checked = uiState.dynamicColor,
            type = SwitchType.CHECK,
            onCheckedChange = { onExecuteIntent(ThemeConfigurationIntent.ChangeDynamicColor(it)) }
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
        ThemeConfigurationScreen(
            uiState = ThemeConfigurationUiState(
                themes = ThemeConfiguration.entries,
                selectedTheme = ThemeConfiguration.AUTO,
                dynamicColor = true
            ),
        )
    }
}