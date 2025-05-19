package com.composetest.feature.exchange.domain.usecase

import com.composetest.feature.exchange.data.repository.ExchangeRepository
import javax.inject.Inject

internal class GetAllExchangesUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) {
    suspend operator fun invoke() = exchangeRepository.getAllExchanges()
}