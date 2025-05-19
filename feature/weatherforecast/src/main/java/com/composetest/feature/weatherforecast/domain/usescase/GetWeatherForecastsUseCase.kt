package com.composetest.feature.weatherforecast.domain.usescase

import com.composetest.common.provider.LocaleProvider
import com.composetest.feature.weatherforecast.data.repository.WeatherForecastRepository
import com.composetest.feature.weatherforecast.domain.model.WeatherForecastLocationModel
import javax.inject.Inject

internal class GetWeatherForecastsUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val localeProvider: LocaleProvider,
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ) = weatherForecastRepository.getWeatherForecasts(
        WeatherForecastLocationModel(
            latitude = latitude,
            longitude = longitude,
            language = localeProvider.currentLanguage
        )
    )
}