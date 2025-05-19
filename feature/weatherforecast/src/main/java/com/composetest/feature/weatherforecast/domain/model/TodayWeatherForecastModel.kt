package com.composetest.feature.weatherforecast.domain.model

internal data class TodayWeatherForecastModel(
    val minTemperature: Float,
    val maxTemperature: Float,
    val temperatures: List<Float>
)
