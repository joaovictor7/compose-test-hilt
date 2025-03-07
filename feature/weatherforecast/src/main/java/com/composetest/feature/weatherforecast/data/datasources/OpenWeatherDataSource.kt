package com.composetest.feature.weatherforecast.data.datasources

import com.composetest.feature.weatherforecast.network.requests.WeatherForecastRequest
import com.composetest.feature.weatherforecast.network.responses.WeatherForecastResponse
import com.composetest.feature.weatherforecast.network.responses.WeatherNowResponse

internal interface OpenWeatherDataSource {
    suspend fun getWeatherNow(request: WeatherForecastRequest): WeatherNowResponse
    suspend fun getWeatherForecasts(request: WeatherForecastRequest): WeatherForecastResponse
}