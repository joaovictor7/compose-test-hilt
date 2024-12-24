package com.composetest.core.domain.usecases

import com.composetest.common.extensions.digits
import com.composetest.common.extensions.toIntOrZero
import com.composetest.common.providers.BuildConfigProvider
import javax.inject.Inject

class CheckRemoteConfigUseCase @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {

    operator fun invoke(versionOrBoolean: String): Boolean {
        val boolean = versionOrBoolean.toBooleanStrictOrNull()
        return boolean ?: (buildConfigProvider.get.fullyVersion.digits.toIntOrZero >= versionOrBoolean.digits.toIntOrZero)
    }
}