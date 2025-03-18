package com.composetest.feature.weatherforecast.presenter.mappers

import com.composetest.common.providers.LocaleProvider
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.feature.weatherforecast.domain.models.WeatherNowModel
import com.composetest.feature.weatherforecast.presenter.models.WeatherNowScreenModel
import javax.inject.Inject

internal class WeatherNowScreenModelMapper @Inject constructor(
    private val localeProvider: LocaleProvider,
    private val buildConfigProvider: BuildConfigProvider,
) {

    fun mapperToModel(weatherNowModel: WeatherNowModel) = WeatherNowScreenModel(
        city = weatherNowModel.city,
        temperature = "${weatherNowModel.temperature.toInt()}º",
        iconUrl = buildConfigProvider.buildConfigFields.openWeatherIconHost.format(weatherNowModel.iconId),
        description = weatherNowModel.description.replaceFirstChar {
            it.titlecase(localeProvider.default)
        }
    )
}