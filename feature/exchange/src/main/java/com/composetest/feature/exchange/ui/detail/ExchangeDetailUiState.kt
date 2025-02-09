package com.composetest.feature.exchange.ui.detail

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.exchange.models.ExchangeDetailRowScreenModel

internal data class ExchangeDetailUiState(
    val exchangeDataRowsScreen: List<ExchangeDetailRowScreenModel> = emptyList(),
) : BaseUiState {
    fun setExchangeDataRowsScreen(exchangeDataRowsScreen: List<ExchangeDetailRowScreenModel>) =
        copy(exchangeDataRowsScreen = exchangeDataRowsScreen)
}
