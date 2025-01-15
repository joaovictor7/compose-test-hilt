package com.composetest.core.data.datasources

import com.composetest.core.data.api.requests.WeatherForecastRequest
import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherNowResponse

internal interface OpenWeatherDataSource {
    suspend fun getWeatherNow(request: WeatherForecastRequest): WeatherNowResponse
    suspend fun getWeatherForecasts(request: WeatherForecastRequest): WeatherForecastResponse
}