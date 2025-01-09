package com.composetest.core.data.api.responses.weatherforecast

import kotlinx.serialization.Serializable

@Serializable
data class WeatherNowDataResponse(
    val icon: String,
    val description: String
)