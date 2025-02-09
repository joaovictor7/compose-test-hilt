package com.composetest.core.data.datasources.local

import com.composetest.core.data.api.responses.ExchangeResponse
import com.composetest.core.data.datasources.CoinDataSource
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils

internal class CoinDataSourceFakeImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider,
) : CoinDataSource {

    override suspend fun getAllExchanges() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<List<ExchangeResponse>>("all-exchanges")
    }
}