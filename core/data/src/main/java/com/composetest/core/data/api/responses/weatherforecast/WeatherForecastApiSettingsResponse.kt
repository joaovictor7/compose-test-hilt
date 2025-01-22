package com.composetest.core.data.api.responses.weatherforecast

import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherForecastApiSettingsResponse(
    val apiId: String,
    val url: String,
    val iconUrl: String
)