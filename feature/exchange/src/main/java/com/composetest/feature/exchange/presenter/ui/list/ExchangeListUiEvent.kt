package com.composetest.feature.exchange.presenter.ui.list

import com.composetest.core.router.model.NavigationModel

internal sealed interface ExchangeListUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : ExchangeListUiEvent
}
