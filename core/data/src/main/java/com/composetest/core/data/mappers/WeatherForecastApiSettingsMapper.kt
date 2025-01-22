package com.composetest.core.data.mappers

import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastApiSettingsResponse
import com.composetest.core.data.utils.decodeJson
import com.composetest.core.domain.models.WeatherForecastApiSettings
import javax.inject.Inject

internal class WeatherForecastApiSettingsMapper @Inject constructor() {

    operator fun invoke(json: String) = decodeJson<WeatherForecastApiSettingsResponse>(json).let {
        WeatherForecastApiSettings(apiId = it.apiId, url = it.url, iconUrl = it.iconUrl)
    }
}