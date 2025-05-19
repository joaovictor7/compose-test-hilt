package com.composetest.feature.weatherforecast.domain.usescase

import com.composetest.common.provider.DateTimeProvider
import com.composetest.feature.weatherforecast.domain.model.DailyWeatherForecastModel
import com.composetest.feature.weatherforecast.domain.model.FutureWeatherForecastModel
import com.composetest.feature.weatherforecast.domain.model.WeatherForecastModel
import javax.inject.Inject

internal class GetFutureWeatherForecastUseCase @Inject constructor(
    private val dateTimeProvider: DateTimeProvider
) {
    operator fun invoke(weatherForecasts: List<WeatherForecastModel>): List<FutureWeatherForecastModel> {
        val futureWeatherForecasts = weatherForecasts
            .filterNot { it.date == dateTimeProvider.currentDate }
            .groupBy { it.date }
        return futureWeatherForecasts.map { futureWeatherForecast ->
            FutureWeatherForecastModel(
                date = futureWeatherForecast.key,
                dailyWeatherForecasts = futureWeatherForecast.value.map {
                    DailyWeatherForecastModel(
                        dateTime = it.dateTime,
                        temperature = it.forecastTemperature.temperature,
                        iconId = it.forecastTemperature.iconId
                    )
                }
            )
        }
    }
}