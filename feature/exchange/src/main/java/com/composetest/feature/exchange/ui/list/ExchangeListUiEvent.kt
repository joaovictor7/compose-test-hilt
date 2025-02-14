package com.composetest.feature.exchange.ui.list

import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface ExchangeListUiEvent : BaseUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : ExchangeListUiEvent
}
