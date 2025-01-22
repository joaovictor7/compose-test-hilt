package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.weatherforecast.WeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel

interface WeatherForecastRepository {
    suspend fun getWeatherNow(): WeatherNowModel
    suspend fun getWeatherForecasts(): List<WeatherForecastModel>
}