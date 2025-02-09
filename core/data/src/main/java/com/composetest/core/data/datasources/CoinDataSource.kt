package com.composetest.core.data.datasources

import com.composetest.core.data.api.responses.ExchangeResponse

internal interface CoinDataSource {
    suspend fun getAllExchanges(): List<ExchangeResponse>
}