package com.composetest.feature.weatherforecast.network.responses

import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherNowDataResponse(
    val icon: String,
    val description: String
)