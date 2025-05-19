package com.composetest.core.domain.usecases.root

import com.composetest.core.domain.enums.Feature
import com.composetest.core.domain.usecases.remoteconfig.GetBooleanRemoteConfigUseCase
import javax.inject.Inject

class GetAvailableFeaturesUseCase @Inject constructor(
    private val getBooleanRemoteConfigUseCase: GetBooleanRemoteConfigUseCase,
) {
    operator fun invoke() = mutableListOf<Feature>().apply {
        Feature.entries.forEach {
            if (getBooleanRemoteConfigUseCase(it.remoteConfig)) {
                add(it)
            }
        }
    }.toList()
}