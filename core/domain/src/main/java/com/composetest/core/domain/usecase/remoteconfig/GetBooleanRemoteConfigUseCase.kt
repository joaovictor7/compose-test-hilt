package com.composetest.core.domain.usecase.remoteconfig

import com.composetest.common.extension.digits
import com.composetest.common.extension.toIntOrZero
import com.composetest.common.remoteconfig.RemoteConfig
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.repository.RemoteConfigRepository
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