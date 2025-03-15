package com.composetest.core.data.repositories

import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.data.datasources.remote.RemoteConfigDataSource
import com.composetest.core.domain.repositories.RemoteConfigRepository
import javax.inject.Inject

internal class RemoteConfigRepositoryImpl @Inject constructor(
    private val remoteConfigDataSource: RemoteConfigDataSource
) : RemoteConfigRepository {

    override fun getString(remoteConfig: RemoteConfig) =
        remoteConfigDataSource.getString(remoteConfig.key)

    override fun getLong(remoteConfig: RemoteConfig) =
        remoteConfigDataSource.getLong(remoteConfig.key)

    override fun getDouble(remoteConfig: RemoteConfig) =
        remoteConfigDataSource.getDouble(remoteConfig.key)

    override fun fetch() {
        remoteConfigDataSource.fetch()
    }
}