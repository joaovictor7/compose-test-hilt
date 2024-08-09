package com.composetest.core.ui.interfaces

import com.composetest.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam

interface BaseUiState {
    val isLoading: Boolean get() = false
    val errorAlertDialogParam: ErrorAlertDialogParam? get() = null
}