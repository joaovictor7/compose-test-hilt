package com.composetest.core.data.datasources.remote

import com.composetest.core.data.datasources.CoinDataSource
import com.composetest.core.data.extensions.get
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.responses.exchange.ExchangeResponse
import io.ktor.client.HttpClient

internal class CoinDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val httpClient: HttpClient,
) : CoinDataSource {

    override suspend fun getAllExchanges() = apiCallUtils.executeApiCall {
        httpClient.get<List<ExchangeResponse>>()
    }
}