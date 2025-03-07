package com.composetest.core.data.datasources.local

import com.composetest.core.data.datasources.OpenWeatherDataSource
import com.composetest.core.data.extensions.readJsonAs
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.requests.WeatherForecastRequest
import com.composetest.core.network.responses.weatherforecast.WeatherForecastResponse
import com.composetest.core.network.responses.weatherforecast.WeatherNowResponse

internal class OpenWeatherFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val assetsProvider: AssetsProvider,
) : OpenWeatherDataSource {

    override suspend fun getWeatherNow(
        request: WeatherForecastRequest
    ) = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<WeatherNowResponse>("open-weather")
    }

    override suspend fun getWeatherForecasts(
        request: WeatherForecastRequest
    ) = apiCallUtils.executeApiCall {
        assetsProvider.readJsonAs<WeatherForecastResponse>("open-weather-forecast")
    }
}