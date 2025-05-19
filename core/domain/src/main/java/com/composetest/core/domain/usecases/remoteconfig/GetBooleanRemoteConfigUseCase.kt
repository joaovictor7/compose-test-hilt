package com.composetest.core.domain.usecases.remoteconfig

import com.composetest.common.extensions.digits
import com.composetest.common.extensions.toIntOrZero
import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.core.domain.repositories.RemoteConfigRepository
import javax.inject.Inject

class GetBooleanRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val buildConfigProvider: BuildConfigProvider
) {
    operator fun invoke(remoteConfig: RemoteConfig): Boolean {
        val versionOrBoolean = remoteConfigRepository.getString(remoteConfig)
        val boolean = versionOrBoolean.toBooleanStrictOrNull()
        val remoteConfigVersionName = versionOrBoolean.digits.toIntOrZero
        val localVersionName = buildConfigProvider.buildConfig.fullyVersion.digits.toIntOrZero
        return boolean ?: (localVersionName >= remoteConfigVersionName)
    }
}