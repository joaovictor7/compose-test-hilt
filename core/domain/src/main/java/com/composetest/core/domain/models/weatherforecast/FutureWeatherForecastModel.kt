package com.composetest.core.domain.models.weatherforecast

import java.time.LocalDate

data class FutureWeatherForecastModel(
    val date: LocalDate,
    val dailyWeatherForecasts: List<DailyWeatherForecastModel>
)