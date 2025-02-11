package com.composetest.feature.weatherforecast.models

internal data class WeatherNowScreenModel(
    val city: String = String(),
    val temperature: String = String(),
    val iconUrl: String = String(),
    val description: String = String()
)
