package com.composetest.core.data.api.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherNowResponse(
    @SerialName("name") val city: String,
    @SerialName("main") val temperatureData: WeatherNowTemperatureResponse,
    @SerialName("weather") val weatherDataList: List<WeatherNowDataResponse>
)