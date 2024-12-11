package com.composetest.core.ui.interfaces

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

interface Screen<UiState, UiEvent, CommandReceiver> {
    @Composable
    operator fun invoke(
        uiState: UiState,
        uiEvent: Flow<UiEvent>?,
        onExecuteCommand: (Command<CommandReceiver>) -> Unit
    )
}