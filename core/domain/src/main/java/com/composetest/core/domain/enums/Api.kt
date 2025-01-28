package com.composetest.core.domain.enums

import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.domain.enums.remoteconfigs.ApiRemoteConfig

enum class Api(val remoteConfig: RemoteConfig) {
    NEWS_API(ApiRemoteConfig.NEWS_API),
    OPEN_WEATHER(ApiRemoteConfig.WEATHER_FORECAST),
    COIN_API(ApiRemoteConfig.COIN_API)
}