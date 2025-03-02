package com.composetest.core.domain.usecases.weatherforecast

import com.composetest.common.providers.LocaleProvider
import com.composetest.core.domain.models.weatherforecast.WeatherForecastLocationModel
import com.composetest.core.domain.repositories.WeatherForecastRepository
import javax.inject.Inject

class GetWeatherNowUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val localeProvider: LocaleProvider
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ) = weatherForecastRepository.getWeatherNow(
        WeatherForecastLocationModel(
            latitude = latitude,
            longitude = longitude,
            language = localeProvider.currentLanguage
        )
    )
}