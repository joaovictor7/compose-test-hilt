package com.composetest.core.data.datasources.local

import com.composetest.core.data.datasources.CoinDataSource
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.responses.exchange.ExchangeResponse

internal class CoinDataSourceFakeImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider,
) : CoinDataSource {

    override suspend fun getAllExchanges() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<List<ExchangeResponse>>("all-exchanges")
    }
}