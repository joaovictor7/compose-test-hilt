package com.composetest.core.data.datasources

import com.composetest.core.network.responses.exchange.ExchangeResponse

internal interface CoinDataSource {
    suspend fun getAllExchanges(): List<ExchangeResponse>
}