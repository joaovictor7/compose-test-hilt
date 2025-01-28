package com.composetest.core.domain.usecases

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.providers.BiometricProvider
import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

class BiometricIsAvailableUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
    private val biometricProvider: BiometricProvider,
) {
    suspend operator fun invoke() =
        configurationRepository.getLastConfiguration()?.biometricLogin.orFalse &&
                biometricProvider.isBiometricAvailable
}