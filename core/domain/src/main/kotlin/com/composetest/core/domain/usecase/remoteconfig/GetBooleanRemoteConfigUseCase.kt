package com.composetest.core.domain.usecase.remoteconfig

import com.composetest.common.api.extension.digits
import com.composetest.common.api.extension.toIntOrZero
import com.composetest.core.domain.interfaces.RemoteConfig
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class GetBooleanRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val buildConfigProvider: BuildConfigProvider
) {
    operator fun invoke(remoteConfig: RemoteConfig): Boolean {
        val versionOrBoolean = remoteConfigRepository.getString(remoteConfig)
            .takeIf { it.isNotBlank() }
            ?: return false
        versionOrBoolean.toBooleanStrictOrNull()?.let { return it }
        val remoteConfigVersionName = versionOrBoolean.digits.toIntOrZero
        val localVersionName = buildConfigProvider.buildConfig.fullyVersion.digits.toIntOrZero
        return localVersionName >= remoteConfigVersionName
    }
}