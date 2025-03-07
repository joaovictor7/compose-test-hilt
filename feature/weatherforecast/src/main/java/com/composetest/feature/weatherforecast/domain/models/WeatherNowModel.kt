package com.composetest.feature.weatherforecast.domain.models

internal data class WeatherNowModel(
    val city: String,
    val temperature: Float,
    val iconId: String,
    val description: String
)
