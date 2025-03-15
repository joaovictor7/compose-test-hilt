package com.composetest.feature.exchange.network.enums

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class ApiKeyRemoteConfig(override val key: String) : RemoteConfig {
    COIN_API("coin_api")
}