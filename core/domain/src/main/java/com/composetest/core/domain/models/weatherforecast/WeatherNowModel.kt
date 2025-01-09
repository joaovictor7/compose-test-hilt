package com.composetest.core.domain.models.weatherforecast

data class WeatherNowModel(
    val city: String,
    val temperature: Float,
    val iconId: String,
    val description: String
)
