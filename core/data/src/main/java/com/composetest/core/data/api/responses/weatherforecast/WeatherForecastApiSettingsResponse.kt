package com.composetest.core.data.api.responses.weatherforecast

import com.composetest.core.data.api.responses.ApiSettingsResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherForecastApiSettingsResponse(
    val apiSettings: ApiSettingsResponse,
    val iconUrl: String,
)