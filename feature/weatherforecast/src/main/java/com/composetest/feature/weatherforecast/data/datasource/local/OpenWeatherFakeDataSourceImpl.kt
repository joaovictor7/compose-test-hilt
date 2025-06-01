package com.composetest.feature.weatherforecast.data.datasource.local

import com.composetest.core.data.extension.readJsonAs
import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.weatherforecast.data.datasource.OpenWeatherDataSource
import com.composetest.feature.weatherforecast.network.request.WeatherForecastRequest
import com.composetest.feature.weatherforecast.network.response.WeatherForecastResponse
import com.composetest.feature.weatherforecast.network.response.WeatherNowResponse

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