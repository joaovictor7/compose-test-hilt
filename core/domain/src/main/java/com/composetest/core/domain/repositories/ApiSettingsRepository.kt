package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.NewsApiSettings
import com.composetest.core.domain.models.WeatherForecastApiSettings

interface ApiSettingsRepository {
    fun getNewsApiSettings(): NewsApiSettings
    fun getWeatherForecastApiSettings(): WeatherForecastApiSettings
}