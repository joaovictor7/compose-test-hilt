package com.composetest.feature.weatherforecast.network

import com.composetest.core.network.ApiSetting

internal data class OpenWeatherApi(
    private val apiId: String,
    override val url: String,
) : ApiSetting {
    override val queryParameters = mapOf(API_ID_PARAM to apiId)

    private companion object {
        const val API_ID_PARAM = "appid"
    }
}