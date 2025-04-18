package com.composetest.feature.account.ui.account

import com.composetest.core.router.interfaces.ResultParam
import com.composetest.core.router.models.NavigationModel

internal sealed interface AccountUiEvent {
    data class NavigateBack(val result: ResultParam) : AccountUiEvent
    data class NavigateTo(val navigationModel: NavigationModel) : AccountUiEvent
}