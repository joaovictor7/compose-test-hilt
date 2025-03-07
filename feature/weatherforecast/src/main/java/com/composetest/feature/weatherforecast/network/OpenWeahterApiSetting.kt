package com.composetest.feature.weatherforecast.network

import com.composetest.core.network.ApiSetting1

internal data class OpenWeatherApi(
    private val apiId: String,
    override val url: String,
) : ApiSetting1 {
    override val queryParameters = mapOf(API_ID_PARAM to apiId)

    private companion object {
        const val API_ID_PARAM = "appid"
    }
}