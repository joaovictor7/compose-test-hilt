package com.composetest.feature.account.presentation.ui.account.viewmodel

import com.composetest.core.router.interfaces.ResultParam
import com.composetest.core.router.model.NavigationModel

internal sealed interface AccountUiEvent {
    data class NavigateBack(val result: ResultParam) : AccountUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : AccountUiEvent
}