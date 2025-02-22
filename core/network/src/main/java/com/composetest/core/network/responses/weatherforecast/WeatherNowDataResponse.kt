package com.composetest.core.network.responses.weatherforecast

import kotlinx.serialization.Serializable

@Serializable
data class WeatherNowDataResponse(
    val icon: String,
    val description: String
)