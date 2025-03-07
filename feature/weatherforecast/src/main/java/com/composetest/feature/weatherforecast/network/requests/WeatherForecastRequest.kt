package com.composetest.feature.weatherforecast.network.requests

internal data class WeatherForecastRequest(
    val latitude: String,
    val longitude: String,
    val language: String,
    val metric: String
)
