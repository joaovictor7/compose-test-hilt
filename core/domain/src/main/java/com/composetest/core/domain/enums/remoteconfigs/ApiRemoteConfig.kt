package com.composetest.core.domain.enums.remoteconfigs

import com.composetest.common.remoteconfig.RemoteConfig

enum class ApiRemoteConfig(override val key: String) : RemoteConfig {
    NEWS_API("api_news_api"),
    WEATHER_FORECAST("api_weather_forecast"),
    COIN_API("api_coin"),
}