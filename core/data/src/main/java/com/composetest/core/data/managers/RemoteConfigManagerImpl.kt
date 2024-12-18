package com.composetest.core.data.managers

import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.remoteconfigs.RemoteConfig
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.domain.usecases.CheckAppVersionUseCase
import javax.inject.Inject

internal class RemoteConfigManagerImpl @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val checkAppVersionUseCase: CheckAppVersionUseCase
) : RemoteConfigManager {

    override fun fetch() = remoteConfigRepository.fetch()

    override fun getBoolean(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getBoolean(remoteConfig.key)

    override fun getDouble(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getDouble(remoteConfig.key)

    override fun getLong(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getLong(remoteConfig.key)

    override fun getString(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getString(remoteConfig.key)

    override fun getBooleanByVersion(remoteConfig: RemoteConfig) =
        checkAppVersionUseCase(getString(remoteConfig))
}