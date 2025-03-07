package com.composetest.feature.weatherforecast.domain.models

internal data class WeatherForecastLocationModel(
    val latitude: Double,
    val longitude: Double,
    val language: String,
) {
    val metric = "metric"
}