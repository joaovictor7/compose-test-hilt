package com.composetest.feature.weatherforecast.domain.usescases

import com.composetest.common.providers.DateTimeProvider
import com.composetest.feature.weatherforecast.domain.models.TodayWeatherForecastModel
import com.composetest.feature.weatherforecast.domain.models.WeatherForecastModel
import javax.inject.Inject

internal class GetTodayWeatherForecastUseCase @Inject constructor(
    private val dateTimeProvider: DateTimeProvider
) {

    operator fun invoke(weatherForecasts: List<WeatherForecastModel>): TodayWeatherForecastModel? {
        val todayWeatherForecasts = weatherForecasts
            .filter { it.date <= dateTimeProvider.currentDate }
        return if (todayWeatherForecasts.isNotEmpty()) {
            TodayWeatherForecastModel(
                minTemperature = todayWeatherForecasts.minOf { it.forecastTemperature.minTemperature },
                maxTemperature = todayWeatherForecasts.maxOf { it.forecastTemperature.maxTemperature },
                temperatures = todayWeatherForecasts.map { it.forecastTemperature.temperature }
            )
        } else null
    }
}