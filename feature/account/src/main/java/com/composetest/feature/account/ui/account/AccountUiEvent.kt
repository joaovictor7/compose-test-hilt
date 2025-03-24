package com.composetest.feature.account.ui.account

import com.composetest.core.router.interfaces.ResultParam

internal sealed interface AccountUiEvent {
    data class NavigateBack(val result: ResultParam) : AccountUiEvent
}