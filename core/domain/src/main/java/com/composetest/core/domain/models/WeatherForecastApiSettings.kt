package com.composetest.core.domain.models

data class WeatherForecastApiSettings(
    val apiSettings: ApiSettings = ApiSettings(),
    val iconUrl: String = String()
)