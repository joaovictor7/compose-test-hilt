package com.composetest.core.domain.usecases.weatherforecast

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.domain.models.weatherforecast.DailyWeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.FutureWeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.WeatherForecastModel
import javax.inject.Inject

class GetFutureWeatherForecastUseCase @Inject constructor(
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