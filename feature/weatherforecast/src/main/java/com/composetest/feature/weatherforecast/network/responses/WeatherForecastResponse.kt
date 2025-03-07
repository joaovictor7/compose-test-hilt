package com.composetest.feature.weatherforecast.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherForecastResponse(
    @SerialName("list") val dataList: List<WeatherForecastDataResponse>
)