package com.composetest.core.domain.usecase.remoteconfig

import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class GetDoubleRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
) {
    operator fun invoke(remoteConfig: RemoteConfig) =
        remoteConfigRepository.getDouble(remoteConfig)
}