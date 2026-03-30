package com.composetest.feature.account.presentation.ui.account.viewmodel

import com.composetest.core.router.model.NavigationModel

internal sealed interface AccountUiEvent {
    data object NavigateBack : AccountUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : AccountUiEvent
}