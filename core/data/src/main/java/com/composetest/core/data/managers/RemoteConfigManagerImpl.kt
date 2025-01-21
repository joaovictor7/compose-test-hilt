package com.composetest.core.data.managers

import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.data.repositories.RemoteConfigRepository
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.usecases.CheckRemoteConfigUseCase
import javax.inject.Inject

internal class RemoteConfigManagerImpl @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val checkRemoteConfigUseCase: CheckRemoteConfigUseCase
) : RemoteConfigManager {

    override fun fetch() = remoteConfigRepository.fetch()

    override fun getBoolean(remoteConfig: RemoteConfig) =
        checkRemoteConfigUseCase(getString(remoteConfig))

    override fun getDouble(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getDouble(remoteConfig.key)

    override fun getLong(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getLong(remoteConfig.key)

    override fun getString(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getString(remoteConfig.key)
}