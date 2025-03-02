package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.CoinDataSource
import com.composetest.core.data.mappers.ExchangeMapper
import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.core.domain.models.exchange.ExchangeModel
import com.composetest.core.domain.repositories.ExchangeRepository
import javax.inject.Inject

internal class ExchangeRepositoryImpl @Inject constructor(
    private val coinDataSourceImpl: CoinDataSource,
    private val exchangeMapper: ExchangeMapper,
) : ExchangeRepository {

    override suspend fun getAllExchanges(): List<ExchangeModel> {
        val response = apiErrorHandler { coinDataSourceImpl.getAllExchanges() }
        return exchangeMapper.mapperToModels(response)
    }
}