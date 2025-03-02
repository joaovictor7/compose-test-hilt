package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.exchange.ExchangeModel

interface ExchangeRepository {
    suspend fun getAllExchanges(): List<ExchangeModel>
}