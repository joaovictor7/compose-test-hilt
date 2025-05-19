package com.composetest.feature.weatherforecast.domain.model

internal data class WeatherNowModel(
    val city: String,
    val temperature: Float,
    val iconId: String,
    val description: String
)
