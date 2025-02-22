package com.composetest.core.network.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataResponse(
    @SerialName("icon") val icon: String
)