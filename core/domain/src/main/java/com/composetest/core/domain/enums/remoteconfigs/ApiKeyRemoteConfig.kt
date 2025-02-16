package com.composetest.core.domain.enums.remoteconfigs

import com.composetest.common.remoteconfig.RemoteConfig

enum class ApiKeyRemoteConfig(override val key: String) : RemoteConfig {
    NEWS_API("news_api"),
    OPEN_WEATHER_API("open_weather_api"),
    COIN_API("coin_api"),
}