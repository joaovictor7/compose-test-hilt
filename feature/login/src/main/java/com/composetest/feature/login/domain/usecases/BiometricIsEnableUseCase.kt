package com.composetest.feature.login.domain.usecases

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

internal class BiometricIsEnableUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
) {
    suspend operator fun invoke() =
        configurationRepository.getLastBiometricConfiguration().orFalse
}