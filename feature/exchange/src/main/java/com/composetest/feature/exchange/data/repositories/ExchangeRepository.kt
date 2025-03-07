package com.composetest.feature.exchange.data.repositories

import com.composetest.feature.exchange.data.mappers.ExchangeMapper
import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.feature.exchange.data.datasources.CoinDataSource
import com.composetest.feature.exchange.domain.models.ExchangeModel
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