package com.composetest.feature.exchange.data.datasource.remote

import com.composetest.core.network.extension.get
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.exchange.data.datasource.CoinDataSource
import com.composetest.feature.exchange.network.response.ExchangeResponse
import io.ktor.client.HttpClient

internal class CoinDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val httpClient: HttpClient,
) : CoinDataSource {

    override suspend fun getAllExchanges() = apiCallUtils.executeApiCall {
        httpClient.get<List<ExchangeResponse>>()
    }
}