package com.composetest.feature.exchange.ui.list

import com.composetest.core.router.models.NavigationModel

internal sealed interface ExchangeListUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : ExchangeListUiEvent
}
