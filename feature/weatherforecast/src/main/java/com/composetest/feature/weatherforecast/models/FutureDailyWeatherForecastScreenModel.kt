package com.composetest.feature.weatherforecast.models

internal data class FutureDailyWeatherForecastScreenModel(
    val iconUrl: String,
    val temperature: String,
    val hour: String
)