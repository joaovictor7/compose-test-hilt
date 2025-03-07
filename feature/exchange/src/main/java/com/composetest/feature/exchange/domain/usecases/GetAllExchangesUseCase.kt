package com.composetest.feature.exchange.domain.usecases

import com.composetest.feature.exchange.data.repositories.ExchangeRepository
import javax.inject.Inject

internal class GetAllExchangesUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) {
    suspend operator fun invoke() = exchangeRepository.getAllExchanges()
}