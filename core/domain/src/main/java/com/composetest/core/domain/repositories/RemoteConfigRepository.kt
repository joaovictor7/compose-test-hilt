package com.composetest.core.domain.repositories

import com.composetest.common.remoteconfig.RemoteConfig

interface RemoteConfigRepository {
    fun getString(key: RemoteConfig): String
    fun getLong(key: RemoteConfig): Long
    fun getDouble(key: RemoteConfig): Double
    fun fetch()
}