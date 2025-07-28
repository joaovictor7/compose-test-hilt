package com.composetest.feature.login.domain.usecase

import com.composetest.common.extension.orFalse
import com.composetest.core.domain.repository.ConfigurationRepository
import javax.inject.Inject

internal class BiometricIsEnableUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
) {
    suspend operator fun invoke() =
        configurationRepository.getLastBiometricConfiguration().orFalse
}