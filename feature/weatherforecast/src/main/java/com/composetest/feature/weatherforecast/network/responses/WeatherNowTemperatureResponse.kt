package com.composetest.feature.weatherforecast.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherNowTemperatureResponse(
    @SerialName("temp") val temperature: Float,
)