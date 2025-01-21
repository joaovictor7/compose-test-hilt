package com.composetest.core.domain.managers

import com.composetest.common.remoteconfig.RemoteConfig

interface RemoteConfigManager {
    fun fetch()

    fun getBoolean(remoteConfig: RemoteConfig): Boolean

    fun getDouble(remoteConfig: RemoteConfig): Double

    fun getLong(remoteConfig: RemoteConfig): Long

    fun getString(remoteConfig: RemoteConfig): String
}