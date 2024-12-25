package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.RemoteConfigDataSource
import javax.inject.Inject

internal class RemoteConfigRepository @Inject constructor(
    private val remoteConfigDataSource: RemoteConfigDataSource
) {

    fun getString(key: String) = remoteConfigDataSource.getString(key)
    fun getLong(key: String) = remoteConfigDataSource.getLong(key)
    fun getDouble(key: String) = remoteConfigDataSource.getDouble(key)
    fun fetch() {
        remoteConfigDataSource.fetch()
    }
}