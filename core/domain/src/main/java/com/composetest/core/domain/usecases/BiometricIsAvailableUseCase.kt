package com.composetest.core.domain.usecases

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.providers.BiometricProvider
import com.composetest.core.domain.remoteconfigs.RemoteConfigKey
import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

class BiometricIsAvailableUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
    private val biometricProvider: BiometricProvider,
    private val remoteConfigManager: RemoteConfigManager
) {
    suspend operator fun invoke() =
        configurationRepository.getLastConfiguration()?.biometricLogin.orFalse &&
                biometricProvider.isBiometricAvailable &&
                remoteConfigManager.getBoolean(RemoteConfigKey.UseBiometric)

}