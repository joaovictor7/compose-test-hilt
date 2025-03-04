package com.composetest.core.domain.usecases.remoteconfigs

import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.domain.repositories.RemoteConfigRepository
import javax.inject.Inject

class GetLongRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
) {
    operator fun invoke(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getLong(remoteConfig.key)
}