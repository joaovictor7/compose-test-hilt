package com.composetest.feature.root.domain.enums

import com.composetest.core.domain.interfaces.RemoteConfig

internal enum class FeatureRemoteConfig(override val key: String) : RemoteConfig {
    HOME("feature_home"),
    WEATHER_FORECAST("feature_weather_forecast"),
    NEWS("feature_news"),
    PROFILE("feature_profile"),
    CONFIGURATION("feature_configuration"),
    EXCHANGE("feature_exchange"),
    PRODUCT("feature_product"),
    FORM("feature_form"),
}