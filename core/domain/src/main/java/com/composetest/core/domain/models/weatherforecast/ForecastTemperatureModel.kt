package com.composetest.core.domain.models.weatherforecast

data class ForecastTemperatureModel(
    val iconId: String,
    val temperature: Float,
    val minTemperature: Float,
    val maxTemperature: Float
)
