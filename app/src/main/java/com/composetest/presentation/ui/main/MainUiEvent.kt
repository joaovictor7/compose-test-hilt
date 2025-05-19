package com.composetest.presentation.ui.main

import com.composetest.core.router.models.NavigationModel

internal interface MainUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : MainUiEvent
}