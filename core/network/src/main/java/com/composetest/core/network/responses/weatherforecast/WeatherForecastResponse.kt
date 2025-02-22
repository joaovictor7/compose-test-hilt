package com.composetest.core.network.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
    @SerialName("list") val dataList: List<WeatherForecastDataResponse>
)