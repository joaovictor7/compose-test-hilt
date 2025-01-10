package com.composetest.feature.configuration.ui.security

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.switches.LabelSwitch
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.enums.switches.SwitchType
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.configuration.R
import kotlinx.coroutines.flow.Flow

internal object ConfigurationSecurityScreen :
    Screen<ConfigurationSecurityUiState, ConfigurationSecurityUiEvent, ConfigurationSecurityCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ConfigurationSecurityUiState,
        uiEvent: Flow<ConfigurationSecurityUiEvent>?,
        onExecuteCommand: (Command<ConfigurationSecurityCommandReceiver>) -> Unit
    ) {
        ScreenScaffold(
            modifier = Modifier.screenMargin(),
            topBar = { LeftTopBar(titleId = R.string.configuration_security_text) }
        ) {
            LabelSwitch(
                labelTextId = R.string.configuration_security_biometric_label_text,
                checked = uiState.biometricIsEnabled,
                switchType = SwitchType.CHECK
            ) { onExecuteCommand(ConfigurationSecurityCommand.ChangeSwitchBiometric(it)) }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        ConfigurationSecurityScreen(
            uiState = ConfigurationSecurityUiState(),
            uiEvent = null
        ) { }
    }
}