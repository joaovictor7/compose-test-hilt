package com.composetest.presentation.ui.main.viewmodel

import com.composetest.core.router.model.NavigationModel

internal interface MainUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : MainUiEvent
}