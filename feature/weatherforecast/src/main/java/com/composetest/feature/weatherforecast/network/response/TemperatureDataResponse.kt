package com.composetest.feature.weatherforecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TemperatureDataResponse(
    @SerialName("temp") val temperature: Float,
    @SerialName("temp_min") val minTemperature: Float,
    @SerialName("temp_max") val maxTemperature: Float
)