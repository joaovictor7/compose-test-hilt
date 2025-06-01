package com.composetest.feature.weatherforecast.data.repository

import com.composetest.core.network.util.apiErrorHandler
import com.composetest.feature.weatherforecast.data.datasource.OpenWeatherDataSource
import com.composetest.feature.weatherforecast.data.mapper.WeatherForecastMapper
import com.composetest.feature.weatherforecast.domain.model.WeatherForecastLocationModel
import com.composetest.feature.weatherforecast.domain.model.WeatherForecastModel
import com.composetest.feature.weatherforecast.domain.model.WeatherNowModel
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