package com.composetest.feature.weatherforecast.domain.usescase

import com.composetest.common.provider.LocaleProvider
import com.composetest.feature.weatherforecast.data.repository.WeatherForecastRepository
import com.composetest.feature.weatherforecast.domain.model.WeatherForecastLocationModel
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