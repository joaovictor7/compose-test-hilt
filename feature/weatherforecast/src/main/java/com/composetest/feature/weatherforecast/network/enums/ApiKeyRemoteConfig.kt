package com.composetest.feature.weatherforecast.network.enums

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class ApiKeyRemoteConfig(override val key: String) : RemoteConfig {
    OPEN_WEATHER_API("open_weather_api")
}