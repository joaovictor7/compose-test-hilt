package com.composetest.feature.weatherforecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherNowResponse(
    @SerialName("name") val city: String,
    @SerialName("main") val temperatureData: WeatherNowTemperatureResponse,
    @SerialName("weather") val weatherDataList: List<WeatherNowDataResponse>
)