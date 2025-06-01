package com.composetest.feature.exchange.data.datasource.local

import com.composetest.core.data.extension.readJsonAs
import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.exchange.data.datasource.CoinDataSource
import com.composetest.feature.exchange.network.response.ExchangeResponse

internal class CoinDataSourceFakeImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider,
) : CoinDataSource {

    override suspend fun getAllExchanges() = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<List<ExchangeResponse>>("all-exchanges")
    }
}