package com.composetest.core.domain.managers

import com.composetest.core.domain.remoteconfigs.RemoteConfig

interface RemoteConfigManager {
    fun fetch()

    fun getBoolean(remoteConfig: RemoteConfig): Boolean

    fun getDouble(remoteConfig: RemoteConfig): Double

    fun getLong(remoteConfig: RemoteConfig): Long

    fun getString(remoteConfig: RemoteConfig): String

    fun getBooleanByVersion(remoteConfig: RemoteConfig): Boolean
}