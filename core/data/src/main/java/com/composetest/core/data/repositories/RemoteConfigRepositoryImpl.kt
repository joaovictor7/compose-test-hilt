package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.FirebaseRemoteConfigDataSource
import com.composetest.core.domain.repositories.RemoteConfigRepository
import javax.inject.Inject

internal class RemoteConfigRepositoryImpl @Inject constructor(
    private val remoteConfigDataSource: FirebaseRemoteConfigDataSource
) : RemoteConfigRepository {

    override fun getString(key: String) = remoteConfigDataSource.getString(key)
    override fun getBoolean(key: String) = remoteConfigDataSource.getBoolean(key)
    override fun getLong(key: String) = remoteConfigDataSource.getLong(key)
    override fun getDouble(key: String) = remoteConfigDataSource.getDouble(key)
    override fun fetch() {
        remoteConfigDataSource.fetch()
    }
}