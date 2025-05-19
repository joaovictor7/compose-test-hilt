package com.composetest.feature.exchange.presenter.ui.list

import com.composetest.core.ui.interfaces.IntentReceiver

internal interface ExchangeListIntentReceiver : IntentReceiver<ExchangeListIntentReceiver> {
    fun getAllExchanges()
    fun navigateToDetail(exchangeId: String)
    fun exchangeFilter(exchange: String)
    fun dismissSimpleDialog()
}