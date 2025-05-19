package com.composetest.feature.weatherforecast.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherForecastDataResponse(
    @SerialName("dt") val dateTime: Long,
    @SerialName("main") val temperatureData: TemperatureDataResponse,
    @SerialName("weather") val weatherData: List<WeatherDataResponse>
)