package com.composetest.core.ui.interfaces

import kotlinx.coroutines.flow.StateFlow

interface UiState<UiState> {
    val uiState: StateFlow<UiState>
}