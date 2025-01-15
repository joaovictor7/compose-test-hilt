package com.composetest.core.data.datasources.local

import com.composetest.core.data.api.requests.WeatherForecastRequest
import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherNowResponse
import com.composetest.core.data.datasources.OpenWeatherDataSource
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils

internal class OpenWeatherFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider,
) : OpenWeatherDataSource {

    override suspend fun getWeatherNow(
        request: WeatherForecastRequest
    ) = apiCallUtils.executeApiCall {
        assetsProvider.getObjectFromJson<WeatherNowResponse>("open-weather")
    }

    override suspend fun getWeatherForecasts(
        request: WeatherForecastRequest
    ) = apiCallUtils.executeApiCall {
        assetsProvider.getObjectFromJson<WeatherForecastResponse>("open-weather-forecast")
    }
}