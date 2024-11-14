package com.composetest.core.domain.usecases

import com.composetest.core.domain.enums.Feature
import com.composetest.core.domain.usecases.remoteconfigs.GetBooleanRemoteConfigUseCase
import javax.inject.Inject

class GetAvailableFeaturesUseCase @Inject constructor(
    private val remoteConfigUseCase: GetBooleanRemoteConfigUseCase,
) {
    operator fun invoke() = mutableListOf<Feature>().apply {
        Feature.entries.forEach {
            if (remoteConfigUseCase(it.remoteConfig)) {
                add(it)
            }
        }
    }.toList()
}