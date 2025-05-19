package com.composetest.core.domain.usecases.remoteconfig

import com.composetest.core.domain.repositories.RemoteConfigRepository
import javax.inject.Inject

class FetchRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) {
    operator fun invoke() = remoteConfigRepository.fetch()
}