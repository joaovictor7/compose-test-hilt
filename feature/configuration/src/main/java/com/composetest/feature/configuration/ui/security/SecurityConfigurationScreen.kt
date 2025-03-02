package com.composetest.feature.configuration.ui.security

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.switches.LabelSwitch
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.enums.switches.SwitchType
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.extensions.toSpanStyle
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.configuration.R

@Composable
internal fun SecurityConfigurationScreen(
    uiState: SecurityConfigurationUiState,
    onExecuteCommand: (Command<SecurityConfigurationCommandReceiver>) -> Unit = {}
) {
    ScreenScaffold(
        modifier = Modifier.screenMargin(),
        topBar = { LeftTopBar(titleId = R.string.configuration_security_text) }
    ) {
        LabelSwitch(
            labelText = getBiometricSwitchLabel(uiState),
            checked = uiState.biometricIsEnabled,
            enable = uiState.biometricIsAvailable,
            switchType = SwitchType.CHECK
        ) { onExecuteCommand(SecurityConfigurationCommand.ChangeSwitchBiometric(it)) }
    }
}

@Composable
private fun getBiometricSwitchLabel(uiState: SecurityConfigurationUiState): AnnotatedString {
    var label =
        AnnotatedString(stringResource(R.string.configuration_security_biometric_label_text))
    if (!uiState.biometricIsAvailable) {
        label = label.plus(
            AnnotatedString(
                stringResource(R.string.configuration_security_biometric_not_available_text),
                MaterialTheme.typography.bodySmall.toSpanStyle(fontStyle = FontStyle.Italic)
            )
        )
    }
    return label
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        SecurityConfigurationScreen(
            uiState = SecurityConfigurationUiState(
                biometricIsAvailable = false
            ),
        )
    }
}