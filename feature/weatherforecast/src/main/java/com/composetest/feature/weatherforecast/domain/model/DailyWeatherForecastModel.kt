package com.composetest.feature.weatherforecast.domain.model

import java.time.LocalDateTime

internal data class DailyWeatherForecastModel(
    val iconId: String,
    val temperature: Float,
    val dateTime: LocalDateTime
)