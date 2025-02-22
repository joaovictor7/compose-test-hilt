package com.composetest.core.network.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherNowTemperatureResponse(
    @SerialName("temp") val temperature: Float,
)