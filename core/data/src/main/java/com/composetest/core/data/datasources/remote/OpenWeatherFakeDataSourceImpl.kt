package com.composetest.core.data.datasources.remote

import com.composetest.core.data.api.requests.WeatherForecastRequest
import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastDataResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastResponse
import com.composetest.core.data.api.responses.weatherforecast.TemperatureDataResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherDataResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherNowDataResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherNowResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherNowTemperatureResponse
import com.composetest.core.data.utils.RemoteCallUtils

internal class OpenWeatherFakeDataSourceImpl(
    private val remoteCallUtils: RemoteCallUtils,
) : OpenWeatherDataSource {

    override suspend fun getWeatherNow(
        request: WeatherForecastRequest
    ) = remoteCallUtils.executeRemoteCall {
        WeatherNowResponse(
            city = "Porto",
            temperatureData = WeatherNowTemperatureResponse(temperature = 2f),
            weatherDataList = listOf(
                WeatherNowDataResponse(icon = "0", description = "Céu limpo")
            )
        )
    }

    override suspend fun getWeatherForecasts(
        request: WeatherForecastRequest
    ) = remoteCallUtils.executeRemoteCall {
        WeatherForecastResponse(
            dataList = listOf(
                WeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = TemperatureDataResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    ),
                    weatherData = listOf(WeatherDataResponse(icon = ""))
                ),
                WeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = TemperatureDataResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    ),
                    weatherData = listOf(WeatherDataResponse(icon = ""))
                ),
                WeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = TemperatureDataResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    ),
                    weatherData = listOf(WeatherDataResponse(icon = ""))
                )
            )
        )
    }
}