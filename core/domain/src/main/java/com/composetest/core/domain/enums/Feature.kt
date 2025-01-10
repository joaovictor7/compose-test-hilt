package com.composetest.core.domain.enums

import com.composetest.core.domain.remoteconfigs.RemoteConfigKey

enum class Feature(internal val remoteConfig: RemoteConfigKey) {
    HOME(RemoteConfigKey.FeatureHome),
    WEATHER_FORECAST(RemoteConfigKey.FeatureWeatherForecast),
    NEWS(RemoteConfigKey.FeatureNews),
    PROFILE(RemoteConfigKey.FeatureProfile),
    CONFIGURATION(RemoteConfigKey.FeatureConfiguration)
}