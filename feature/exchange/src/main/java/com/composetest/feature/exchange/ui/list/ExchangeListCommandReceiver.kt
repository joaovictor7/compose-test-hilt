package com.composetest.feature.exchange.ui.list

import com.composetest.core.ui.interfaces.CommandReceiver

internal interface ExchangeListCommandReceiver : CommandReceiver<ExchangeListCommandReceiver> {
    fun getAllExchanges()
    fun navigateToDetail(exchangeId: String)
    fun exchangeFilter(exchange: String)
    fun dismissSimpleDialog()
}