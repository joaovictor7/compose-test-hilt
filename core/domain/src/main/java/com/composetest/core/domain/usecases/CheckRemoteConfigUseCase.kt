package com.composetest.core.domain.usecases

import com.composetest.common.extensions.digits
import com.composetest.common.extensions.toIntOrZero
import com.composetest.core.domain.providers.BuildConfigProvider
import javax.inject.Inject

class CheckRemoteConfigUseCase @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {

    operator fun invoke(versionOrBoolean: String): Boolean {
        val boolean = versionOrBoolean.toBooleanStrictOrNull()
        val remoteConfigVersionName = versionOrBoolean.digits.toIntOrZero
        val localVersionName = buildConfigProvider.buildConfig.fullyVersion.digits.toIntOrZero
        return boolean ?: (localVersionName >= remoteConfigVersionName)
    }
}