package com.composetest.feature.exchange.presenter.ui.detail

import com.composetest.feature.exchange.presenter.models.ExchangeDetailRowScreenModel

internal data class ExchangeDetailUiState(
    val exchangeDataRowsScreen: List<ExchangeDetailRowScreenModel> = emptyList(),
) {
    fun setExchangeDataRowsScreen(exchangeDataRowsScreen: List<ExchangeDetailRowScreenModel>) =
        copy(exchangeDataRowsScreen = exchangeDataRowsScreen)
}
