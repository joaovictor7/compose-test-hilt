package com.composetest.core.domain.usecases

import com.composetest.core.domain.enums.Feature
import com.composetest.core.domain.managers.RemoteConfigManager
import javax.inject.Inject

class GetAvailableFeaturesUseCase @Inject constructor(
    private val remoteConfigManager: RemoteConfigManager,
) {
    operator fun invoke() = mutableListOf<Feature>().apply {
        Feature.entries.forEach {
            if (remoteConfigManager.getBoolean(it.remoteConfig)) {
                add(it)
            }
        }
    }.toList()
}