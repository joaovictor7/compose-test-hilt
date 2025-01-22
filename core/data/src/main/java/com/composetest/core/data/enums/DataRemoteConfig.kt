package com.composetest.core.data.enums

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class DataRemoteConfig(override val key: String) : RemoteConfig {
    API_NEWS_API("api_news_api"),
    API_WEATHER_FORECAST("api_weather_forecast"),
}