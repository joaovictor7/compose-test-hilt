package com.composetest.feature.weatherforecast.presenter.mapper

import com.composetest.common.provider.LocaleProvider
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.feature.weatherforecast.domain.model.WeatherNowModel
import com.composetest.feature.weatherforecast.presenter.model.WeatherNowScreenModel
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