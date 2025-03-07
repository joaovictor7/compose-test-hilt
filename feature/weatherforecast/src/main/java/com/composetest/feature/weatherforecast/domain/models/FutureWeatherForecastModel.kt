package com.composetest.feature.weatherforecast.domain.models

import java.time.LocalDate

internal data class FutureWeatherForecastModel(
    val date: LocalDate,
    val dailyWeatherForecasts: List<DailyWeatherForecastModel>
)