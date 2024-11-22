package com.composetest.core.domain.managers

import com.composetest.core.domain.interfaces.RemoteConfig

interface RemoteConfigManager {
    fun getBoolean(remoteConfig: RemoteConfig): Boolean

    fun getDouble(remoteConfig: RemoteConfig): Double

    fun getLong(remoteConfig: RemoteConfig): Long

    fun getString(remoteConfig: RemoteConfig): String

    fun getBooleanByVersion(remoteConfig: RemoteConfig): Boolean
}