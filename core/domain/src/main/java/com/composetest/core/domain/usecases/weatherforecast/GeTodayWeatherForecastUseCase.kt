package com.composetest.core.domain.usecases.weatherforecast

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.domain.models.weatherforecast.TodayWeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.WeatherForecastModel
import javax.inject.Inject

class GeTodayWeatherForecastUseCase @Inject constructor(
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