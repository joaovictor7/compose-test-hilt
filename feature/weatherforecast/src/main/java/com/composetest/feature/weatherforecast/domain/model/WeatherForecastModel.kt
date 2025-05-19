package com.composetest.feature.weatherforecast.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

internal data class WeatherForecastModel(
    val dateTime: LocalDateTime,
    val forecastTemperature: ForecastTemperatureModel
) {
    val date: LocalDate = dateTime.toLocalDate()
}
