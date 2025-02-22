package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.RemoteConfigDataSource
import com.composetest.core.domain.repositories.RemoteConfigRepository
import javax.inject.Inject

internal class RemoteConfigRepositoryImpl @Inject constructor(
    private val remoteConfigDataSource: RemoteConfigDataSource
): RemoteConfigRepository {

    override fun getString(key: String) = remoteConfigDataSource.getString(key)
    override fun getLong(key: String) = remoteConfigDataSource.getLong(key)
    override fun getDouble(key: String) = remoteConfigDataSource.getDouble(key)
    override fun fetch() {
        remoteConfigDataSource.fetch()
    }
}