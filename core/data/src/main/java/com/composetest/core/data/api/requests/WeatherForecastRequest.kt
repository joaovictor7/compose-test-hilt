package com.composetest.core.data.api.requests

data class WeatherForecastRequest(
    val latitude: String,
    val longitude: String,
    val language: String,
    val metric: String
)
