package com.composetest.core.data.mappers

import com.composetest.core.data.api.responses.weatherforecast.WeatherForecastApiSettingsResponse
import com.composetest.core.data.utils.decodeJson
import com.composetest.core.domain.models.WeatherForecastApiSettings
import javax.inject.Inject

internal class WeatherForecastApiSettingsMapper @Inject constructor(
    private val apiSettingsMapper: ApiSettingsMapper
) {

    operator fun invoke(json: String) = decodeJson<WeatherForecastApiSettingsResponse>(json).let {
        WeatherForecastApiSettings(
            apiSettings = apiSettingsMapper(it.apiSettings),
            iconUrl = it.iconUrl
        )
    }
}