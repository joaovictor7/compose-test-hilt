package com.composetest.feature.exchange.presenter.ui.list

import com.composetest.core.ui.interfaces.Intent

internal sealed interface ExchangeListIntent : Intent<ExchangeListIntentReceiver> {
    data object GetAllExchanges : ExchangeListIntent {
        override fun execute(intentReceiver: ExchangeListIntentReceiver) {
            intentReceiver.getAllExchanges()
        }
    }

    data object DismissSimpleDialog : ExchangeListIntent {
        override fun execute(intentReceiver: ExchangeListIntentReceiver) {
            intentReceiver.dismissSimpleDialog()
        }
    }

    data class NavigateToDetail(private val exchangeId: String) : ExchangeListIntent {
        override fun execute(intentReceiver: ExchangeListIntentReceiver) {
            intentReceiver.navigateToDetail(exchangeId)
        }
    }

    data class ExchangeFilter(private val exchange: String) : ExchangeListIntent {
        override fun execute(intentReceiver: ExchangeListIntentReceiver) {
            intentReceiver.exchangeFilter(exchange)
        }
    }
}