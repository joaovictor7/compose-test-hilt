package com.composetest.feature.root.domain.usecase

import com.composetest.core.domain.usecase.remoteconfig.GetBooleanRemoteConfigUseCase
import com.composetest.feature.root.domain.enums.Feature
import javax.inject.Inject

internal class GetAvailableFeaturesUseCase @Inject constructor(
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