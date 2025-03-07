package com.composetest.feature.exchange.presenter.ui.list

import com.composetest.core.ui.interfaces.Command

internal sealed interface ExchangeListCommand : Command<ExchangeListCommandReceiver> {
    data object GetAllExchanges : ExchangeListCommand {
        override fun execute(commandReceiver: ExchangeListCommandReceiver) {
            commandReceiver.getAllExchanges()
        }
    }

    data object DismissSimpleDialog : ExchangeListCommand {
        override fun execute(commandReceiver: ExchangeListCommandReceiver) {
            commandReceiver.dismissSimpleDialog()
        }
    }

    data class NavigateToDetail(private val exchangeId: String) : ExchangeListCommand {
        override fun execute(commandReceiver: ExchangeListCommandReceiver) {
            commandReceiver.navigateToDetail(exchangeId)
        }
    }

    data class ExchangeFilter(private val exchange: String) : ExchangeListCommand {
        override fun execute(commandReceiver: ExchangeListCommandReceiver) {
            commandReceiver.exchangeFilter(exchange)
        }
    }
}