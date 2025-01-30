package com.composetest.core.domain.models.weatherforecast

data class WeatherForecastLocationModel(
    val latitude: Double,
    val longitude: Double,
    val language: String,
) {
    val metric = "metric"
}