package com.composetest.core.domain.usecases

import com.composetest.core.domain.repositories.WeatherForecastRepository
import javax.inject.Inject

class GetWeatherForecastsUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
) {
    suspend operator fun invoke() = weatherForecastRepository.getWeatherForecasts()
}