package com.composetest.feature.configuration.presenter.ui.security

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.component.scaffold.ScreenScaffold
import com.composetest.core.designsystem.component.switch.LabelSwitch
import com.composetest.core.designsystem.component.topbar.LeftTopBar
import com.composetest.core.designsystem.enums.switchs.SwitchType
import com.composetest.core.designsystem.extension.screenMargin
import com.composetest.core.designsystem.extension.toSpanStyle
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.configuration.R

@Composable
internal fun SecurityConfigurationScreen(
    uiState: SecurityConfigurationUiState,
    onExecuteIntent: (Intent<SecurityConfigurationIntentReceiver>) -> Unit = {}
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
        ) { onExecuteIntent(SecurityConfigurationIntent.ChangeSwitchBiometric(it)) }
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