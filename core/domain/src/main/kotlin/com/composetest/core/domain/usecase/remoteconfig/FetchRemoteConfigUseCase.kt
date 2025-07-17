package com.composetest.core.domain.usecase.remoteconfig

import com.composetest.core.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class FetchRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) {
    operator fun invoke() = remoteConfigRepository.fetch()
}