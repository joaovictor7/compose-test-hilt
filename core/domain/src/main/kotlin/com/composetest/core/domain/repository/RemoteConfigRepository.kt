package com.composetest.core.domain.repository

import com.composetest.core.domain.interfaces.RemoteConfig

interface RemoteConfigRepository {
    fun getString(remoteConfig: RemoteConfig): String
    fun getLong(remoteConfig: RemoteConfig): Long
    fun getDouble(remoteConfig: RemoteConfig): Double
    fun fetch()
}