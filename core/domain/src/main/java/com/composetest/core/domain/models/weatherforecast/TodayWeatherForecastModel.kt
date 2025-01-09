package com.composetest.core.domain.models.weatherforecast

data class TodayWeatherForecastModel(
    val minTemperature: Float,
    val maxTemperature: Float,
    val temperatures: List<Float>
)
