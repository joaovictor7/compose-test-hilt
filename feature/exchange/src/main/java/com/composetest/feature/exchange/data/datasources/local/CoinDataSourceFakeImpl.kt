package com.composetest.feature.exchange.data.datasources.local

import com.composetest.core.data.extensions.readJsonAs
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.feature.exchange.data.datasources.CoinDataSource
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.feature.exchange.network.responses.ExchangeResponse

internal class CoinDataSourceFakeImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider,
) : CoinDataSource {

    override suspend fun getAllExchanges() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<List<ExchangeResponse>>("all-exchanges")
    }
}