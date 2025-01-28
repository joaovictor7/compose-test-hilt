package com.composetest.core.domain.repositories

import com.composetest.core.domain.enums.Api
import com.composetest.core.domain.models.ApiSettings
import com.composetest.core.domain.models.WeatherForecastApiSettings

interface ApiSettingsRepository {
    fun getApiSettings(api: Api): ApiSettings
    fun getWeatherForecastApiSettings(): WeatherForecastApiSettings
}