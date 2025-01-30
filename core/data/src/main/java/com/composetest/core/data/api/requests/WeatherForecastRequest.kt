package com.composetest.core.data.api.requests

internal data class WeatherForecastRequest(
    val latitude: String,
    val longitude: String,
    val language: String,
    val metric: String
)
