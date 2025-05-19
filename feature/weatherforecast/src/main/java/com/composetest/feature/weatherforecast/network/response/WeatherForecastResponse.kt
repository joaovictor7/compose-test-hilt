package com.composetest.feature.weatherforecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherForecastResponse(
    @SerialName("list") val dataList: List<WeatherForecastDataResponse>
)