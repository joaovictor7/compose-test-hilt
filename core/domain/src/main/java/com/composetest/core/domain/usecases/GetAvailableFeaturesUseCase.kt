package com.composetest.core.domain.usecases

import android.util.Log
import com.composetest.core.domain.enums.Feature
import com.composetest.core.domain.managers.RemoteConfigManager
import javax.inject.Inject

class GetAvailableFeaturesUseCase @Inject constructor(
    private val remoteConfigManager: RemoteConfigManager,
) {
    operator fun invoke() = mutableListOf<Feature>().apply {
        Feature.entries.forEach {
            Log.d("test", it.name)
            runCatching {
                if (remoteConfigManager.getBooleanByVersion(it.remoteConfig)) {
                    add(it)
                }
            }.onFailure {
                val e = it
            }
        }
    }.toList()
}