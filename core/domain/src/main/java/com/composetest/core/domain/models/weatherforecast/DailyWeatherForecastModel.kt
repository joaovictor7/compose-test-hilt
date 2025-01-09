package com.composetest.core.domain.models.weatherforecast

import java.time.LocalDateTime

data class DailyWeatherForecastModel(
    val iconId: String,
    val temperature: Float,
    val dateTime: LocalDateTime
)