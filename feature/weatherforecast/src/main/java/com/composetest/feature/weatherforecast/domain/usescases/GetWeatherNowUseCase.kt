package com.composetest.feature.weatherforecast.domain.usescases

import com.composetest.common.providers.LocaleProvider
import com.composetest.feature.weatherforecast.data.repositories.WeatherForecastRepository
import com.composetest.feature.weatherforecast.domain.models.WeatherForecastLocationModel
import javax.inject.Inject

internal class GetWeatherNowUseCase @Inject constructor(
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