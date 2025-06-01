package com.composetest.feature.exchange.data.repository

import com.composetest.core.network.util.apiErrorHandler
import com.composetest.feature.exchange.data.datasource.CoinDataSource
import com.composetest.feature.exchange.data.mapper.ExchangeMapper
import com.composetest.feature.exchange.domain.model.ExchangeModel
import javax.inject.Inject

internal class ExchangeRepository @Inject constructor(
    private val coinDataSource: CoinDataSource,
    private val exchangeMapper: ExchangeMapper,
) {

    suspend fun getAllExchanges(): List<ExchangeModel> {
        val response = apiErrorHandler { coinDataSource.getAllExchanges() }
        return exchangeMapper.mapperToModels(response)
    }
}