package com.composetest.core.data.managers

import com.composetest.common.extensions.orFalse
import com.composetest.core.data.remoteconfig.RemoteConfigKey
import com.composetest.core.data.repositories.BiometricRepository
import com.composetest.core.domain.managers.BiometricManager
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.security.providers.BiometricsProvider
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class BiometricManagerImpl @Inject constructor(
    private val biometricRepository: BiometricRepository,
    private val biometricProvider: BiometricsProvider,
    private val remoteConfigManager: RemoteConfigManager
) : BiometricManager {

    override suspend fun setBiometric(use: Boolean) {
        biometricRepository.setUseBiometric(use)
    }

    override suspend fun biometricIsEnabled() =
        remoteConfigManager.getBoolean(RemoteConfigKey.UseBiometric) &&
                biometricProvider.isBiometricAvailable &&
                biometricRepository.biometricIsSet.firstOrNull().orFalse
}