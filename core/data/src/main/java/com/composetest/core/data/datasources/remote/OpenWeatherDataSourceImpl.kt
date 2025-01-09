package com.composetest.core.data.datasources.remote

import com.composetest.core.data.api.requests.WeatherForecastRequest
import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastResponse
import com.composetest.core.data.api.responses.weatherforecast.WeatherNowResponse
import com.composetest.core.data.extensions.get
import com.composetest.core.data.utils.RemoteCallUtils
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder

internal class OpenWeatherDataSourceImpl(
    private val remoteCallUtils: RemoteCallUtils,
    private val openWeatherApi: HttpClient,
) : OpenWeatherDataSource {

    override suspend fun getWeatherNow(request: WeatherForecastRequest) =
        remoteCallUtils.executeRemoteCall {
            openWeatherApi.get<WeatherNowResponse>(WEATHER_URL) {
                appendParameters(request)
            }
        }

    override suspend fun getWeatherForecasts(
        request: WeatherForecastRequest
    ) = remoteCallUtils.executeRemoteCall {
        openWeatherApi.get<WeatherForecastResponse>(FORECAST_URL) {
            appendParameters(request)
        }
    }

    private fun HttpRequestBuilder.appendParameters(request: WeatherForecastRequest) {
        url {
            with(parameters) {
                append("lat", request.latitude)
                append("lon", request.longitude)
                append("lang", request.language)
                append("units", request.metric)
            }
        }
    }

    private companion object {
        const val WEATHER_URL = "weather"
        const val FORECAST_URL = "forecast"
    }
}
