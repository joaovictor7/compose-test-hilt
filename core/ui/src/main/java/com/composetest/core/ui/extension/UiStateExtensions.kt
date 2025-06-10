package com.composetest.core.ui.extension

import com.composetest.core.ui.interfaces.UiState

val <T> UiState<T>.uiStateValue get() = uiState.value