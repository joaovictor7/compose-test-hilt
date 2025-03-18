package com.composetest.feature.exchange.data.datasources.remote

import com.composetest.core.data.extensions.get
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.feature.exchange.data.datasources.CoinDataSource
import com.composetest.feature.exchange.network.responses.ExchangeResponse
import io.ktor.client.HttpClient

internal class CoinDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val httpClient: HttpClient,
) : CoinDataSource {

    override suspend fun getAllExchanges() = apiCallUtils.executeApiCall {
        httpClient.get<List<ExchangeResponse>>()
    }
}