package com.composetest.feature.weatherforecast.domain.models

internal data class ForecastTemperatureModel(
    val iconId: String,
    val temperature: Float,
    val minTemperature: Float,
    val maxTemperature: Float
)
