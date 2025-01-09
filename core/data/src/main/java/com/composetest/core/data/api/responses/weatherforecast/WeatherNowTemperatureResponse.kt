package com.composetest.core.data.api.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherNowTemperatureResponse(
    @SerialName("temp") val temperature: Float,
)