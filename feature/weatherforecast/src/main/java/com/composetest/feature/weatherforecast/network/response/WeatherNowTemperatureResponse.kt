package com.composetest.feature.weatherforecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherNowTemperatureResponse(
    @SerialName("temp") val temperature: Float,
)