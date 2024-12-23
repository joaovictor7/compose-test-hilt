package com.composetest.core.domain.usecases

import com.composetest.common.extensions.digits
import com.composetest.common.extensions.toIntOrZero
import com.composetest.common.providers.BuildConfigProvider
import javax.inject.Inject

class CheckAppVersionUseCase @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {

    operator fun invoke(versionToCompare: String) =
        buildConfigProvider.get.fullyVersion.digits.toIntOrZero >= versionToCompare.digits.toIntOrZero
}