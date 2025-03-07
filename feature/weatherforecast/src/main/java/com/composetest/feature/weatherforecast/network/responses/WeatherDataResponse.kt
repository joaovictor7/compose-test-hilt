package com.composetest.feature.weatherforecast.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherDataResponse(
    @SerialName("icon") val icon: String
)