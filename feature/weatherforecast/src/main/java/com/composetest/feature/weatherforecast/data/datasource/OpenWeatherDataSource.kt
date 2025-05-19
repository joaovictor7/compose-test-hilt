package com.composetest.feature.weatherforecast.data.datasource

import com.composetest.feature.weatherforecast.network.request.WeatherForecastRequest
import com.composetest.feature.weatherforecast.network.response.WeatherForecastResponse
import com.composetest.feature.weatherforecast.network.response.WeatherNowResponse

internal interface OpenWeatherDataSource {
    suspend fun getWeatherNow(request: WeatherForecastRequest): WeatherNowResponse
    suspend fun getWeatherForecasts(request: WeatherForecastRequest): WeatherForecastResponse
}