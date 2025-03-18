package com.composetest.feature.weatherforecast.data.repositories

import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.feature.weatherforecast.data.datasources.OpenWeatherDataSource
import com.composetest.feature.weatherforecast.data.mappers.WeatherForecastMapper
import com.composetest.feature.weatherforecast.domain.models.WeatherForecastLocationModel
import com.composetest.feature.weatherforecast.domain.models.WeatherForecastModel
import com.composetest.feature.weatherforecast.domain.models.WeatherNowModel
import javax.inject.Inject

internal class WeatherForecastRepository @Inject constructor(
    private val openWeatherDataSource: OpenWeatherDataSource,
    private val weatherForecastMapper: WeatherForecastMapper,
) {

    suspend fun getWeatherNow(
        weatherForecastLocation: WeatherForecastLocationModel
    ): WeatherNowModel {
        val request = weatherForecastMapper.mapperToRequest(weatherForecastLocation)
        val response = apiErrorHandler { openWeatherDataSource.getWeatherNow(request) }
        return weatherForecastMapper.mapperToModel(response)
    }

    suspend fun getWeatherForecasts(
        weatherForecastLocation: WeatherForecastLocationModel
    ): List<WeatherForecastModel> {
        val request = weatherForecastMapper.mapperToRequest(weatherForecastLocation)
        val response = apiErrorHandler {
            openWeatherDataSource.getWeatherForecasts(request)
        }
        return weatherForecastMapper.mapperToModels(response)
    }
}