package com.composetest.feature.exchange.data.datasources

import com.composetest.feature.exchange.network.responses.ExchangeResponse

internal interface CoinDataSource {
    suspend fun getAllExchanges(): List<ExchangeResponse>
}