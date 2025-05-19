package com.composetest.feature.weatherforecast.domain.model

internal data class WeatherForecastLocationModel(
    val latitude: Double,
    val longitude: Double,
    val language: String,
) {
    val metric = "metric"
}