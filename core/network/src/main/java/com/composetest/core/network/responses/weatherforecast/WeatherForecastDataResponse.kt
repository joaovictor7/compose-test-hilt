package com.composetest.core.network.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastDataResponse(
    @SerialName("dt") val dateTime: Long,
    @SerialName("main") val temperatureData: TemperatureDataResponse,
    @SerialName("weather") val weatherData: List<WeatherDataResponse>
)