package com.composetest.feature.weatherforecast.mappers

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.providers.LocaleProvider
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel
import javax.inject.Inject

internal class WeatherNowScreenModelMapper @Inject constructor(
    private val localeProvider: LocaleProvider,
    private val buildConfigProvider: BuildConfigProvider
) {

    operator fun invoke(weatherNowModel: WeatherNowModel) = WeatherNowScreenModel(
        city = weatherNowModel.city,
        temperature = "${weatherNowModel.temperature.toInt()}º",
        iconUrl = getIconUrl(weatherNowModel.iconId),
        description = weatherNowModel.description.replaceFirstChar {
            it.titlecase(localeProvider.default)
        }
    )

    private fun getIconUrl(iconId: String) =
        buildConfigProvider.get.buildConfigFields.openWeatherIconHost.format(iconId)
}