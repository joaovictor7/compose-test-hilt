package com.composetest.feature.weatherforecast.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherNowDataResponse(
    val icon: String,
    val description: String
)