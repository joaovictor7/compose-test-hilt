package com.composetest.feature.exchange.data.datasource

import com.composetest.feature.exchange.network.response.ExchangeResponse

internal interface CoinDataSource {
    suspend fun getAllExchanges(): List<ExchangeResponse>
}