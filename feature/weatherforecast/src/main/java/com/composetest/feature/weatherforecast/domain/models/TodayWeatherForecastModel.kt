package com.composetest.feature.weatherforecast.domain.models

internal data class TodayWeatherForecastModel(
    val minTemperature: Float,
    val maxTemperature: Float,
    val temperatures: List<Float>
)
