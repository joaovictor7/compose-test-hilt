package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.ExchangeModel

interface ExchangeRepository {
    suspend fun getAllExchanges(): List<ExchangeModel>
}