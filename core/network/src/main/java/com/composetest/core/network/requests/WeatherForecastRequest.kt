package com.composetest.core.network.requests

data class WeatherForecastRequest(
    val latitude: String,
    val longitude: String,
    val language: String,
    val metric: String
)
