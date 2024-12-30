package com.composetest.feature.configuration.ui.security

import androidx.compose.runtime.Composable
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.topbar.LeftTopBar
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
            topBar = { LeftTopBar(titleId = R.string.configuration_security_text) }
        ) {

        }
    }
}