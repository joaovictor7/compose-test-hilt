package com.composetest.feature.weatherforecast.models

internal data class FutureWeatherForecastScreenModel(
    val day: String,
    val futureDailyWeatherForecasts: List<FutureDailyWeatherForecastScreenModel>
)