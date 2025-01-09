package com.composetest.core.data.api.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherForecastResponse(
    @SerialName("list") val dataList: List<WeatherForecastDataResponse>
)