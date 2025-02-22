package com.composetest.core.data.datasources

import com.composetest.core.network.requests.WeatherForecastRequest
import com.composetest.core.network.responses.weatherforecast.WeatherForecastResponse
import com.composetest.core.network.responses.weatherforecast.WeatherNowResponse

internal interface OpenWeatherDataSource {
    suspend fun getWeatherNow(request: WeatherForecastRequest): WeatherNowResponse
    suspend fun getWeatherForecasts(request: WeatherForecastRequest): WeatherForecastResponse
}