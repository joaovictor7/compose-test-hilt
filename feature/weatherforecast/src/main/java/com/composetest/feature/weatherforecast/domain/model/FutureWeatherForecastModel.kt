package com.composetest.feature.weatherforecast.domain.model

import java.time.LocalDate

internal data class FutureWeatherForecastModel(
    val date: LocalDate,
    val dailyWeatherForecasts: List<DailyWeatherForecastModel>
)