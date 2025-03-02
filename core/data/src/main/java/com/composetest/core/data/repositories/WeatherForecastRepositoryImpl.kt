package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.OpenWeatherDataSource
import com.composetest.core.data.mappers.WeatherForecastMapper
import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.core.domain.models.weatherforecast.WeatherForecastLocationModel
import com.composetest.core.domain.models.weatherforecast.WeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel
import com.composetest.core.domain.repositories.WeatherForecastRepository
import javax.inject.Inject

internal class WeatherForecastRepositoryImpl @Inject constructor(
    private val openWeatherDataSource: OpenWeatherDataSource,
    private val weatherForecastMapper: WeatherForecastMapper,
) : WeatherForecastRepository {

    override suspend fun getWeatherNow(
        weatherForecastLocation: WeatherForecastLocationModel
    ): WeatherNowModel {
        val request = weatherForecastMapper.mapperToRequest(weatherForecastLocation)
        val response = apiErrorHandler { openWeatherDataSource.getWeatherNow(request) }
        return weatherForecastMapper.mapperToModel(response)
    }

    override suspend fun getWeatherForecasts(
        weatherForecastLocation: WeatherForecastLocationModel
    ): List<WeatherForecastModel> {
        val request = weatherForecastMapper.mapperToRequest(weatherForecastLocation)
        val response = apiErrorHandler {
            openWeatherDataSource.getWeatherForecasts(request)
        }
        return weatherForecastMapper.mapperToModels(response)
    }
}