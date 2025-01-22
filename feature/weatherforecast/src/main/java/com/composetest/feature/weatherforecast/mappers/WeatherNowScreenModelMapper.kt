package com.composetest.feature.weatherforecast.mappers

import com.composetest.common.providers.LocaleProvider
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel
import com.composetest.core.domain.usecases.GetWeatherForecastIconUrlUseCase
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel
import javax.inject.Inject

internal class WeatherNowScreenModelMapper @Inject constructor(
    private val localeProvider: LocaleProvider,
    private val getWeatherForecastIconUrlUseCase: GetWeatherForecastIconUrlUseCase,
) {

    operator fun invoke(weatherNowModel: WeatherNowModel): WeatherNowScreenModel {
        val iconUrl = getWeatherForecastIconUrlUseCase()
        return WeatherNowScreenModel(
            city = weatherNowModel.city,
            temperature = "${weatherNowModel.temperature.toInt()}º",
            iconUrl = iconUrl.format(weatherNowModel.iconId),
            description = weatherNowModel.description.replaceFirstChar {
                it.titlecase(localeProvider.default)
            }
        )
    }
}