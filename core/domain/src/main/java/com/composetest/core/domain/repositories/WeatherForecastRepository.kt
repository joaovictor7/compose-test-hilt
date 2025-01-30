package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.weatherforecast.WeatherForecastLocationModel
import com.composetest.core.domain.models.weatherforecast.WeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel

interface WeatherForecastRepository {
    suspend fun getWeatherNow(
        weatherForecastLocation: WeatherForecastLocationModel
    ): WeatherNowModel

    suspend fun getWeatherForecasts(
        weatherForecastLocation: WeatherForecastLocationModel
    ): List<WeatherForecastModel>
}