package com.composetest.feature.news.network.enums

import com.composetest.common.remoteconfig.RemoteConfig

internal enum class ApiKeyRemoteConfig(override val key: String) : RemoteConfig {
    NEWS_API("news_api")
}