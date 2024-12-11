package com.composetest.core.ui.interfaces

import androidx.compose.runtime.Composable

interface Screen<UiState, CommandReceiver> {
    @Composable
    operator fun invoke(
        uiState: UiState,
        onExecuteCommand: (Command<CommandReceiver>) -> Unit
    )
}

interface Screen2<UiState, UiEvent, CommandReceiver> {
    @Composable
    operator fun invoke(
        uiState: UiState,
        uiEvent: UiEvent? = null,
        onExecuteCommand: (Command<CommandReceiver>) -> Unit
    )
}