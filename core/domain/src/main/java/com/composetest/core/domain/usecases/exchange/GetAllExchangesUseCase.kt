package com.composetest.core.domain.usecases.exchange

import com.composetest.core.domain.repositories.ExchangeRepository
import javax.inject.Inject

class GetAllExchangesUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) {
    suspend operator fun invoke() = exchangeRepository.getAllExchanges()
}