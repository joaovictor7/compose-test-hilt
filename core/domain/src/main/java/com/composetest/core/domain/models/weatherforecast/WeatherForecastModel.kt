package com.composetest.core.domain.models.weatherforecast

import java.time.LocalDate
import java.time.LocalDateTime

data class WeatherForecastModel(
    val dateTime: LocalDateTime,
    val forecastTemperature: ForecastTemperatureModel
) {
    val date: LocalDate = dateTime.toLocalDate()
}
