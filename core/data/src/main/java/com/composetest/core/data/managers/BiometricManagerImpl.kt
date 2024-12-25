package com.composetest.core.data.managers

import com.composetest.common.extensions.orFalse
import com.composetest.core.data.remoteconfig.RemoteConfigKey
import com.composetest.core.data.repositories.BiometricRepository
import com.composetest.core.domain.managers.BiometricManager
import com.composetest.core.domain.managers.RemoteConfigManager
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class BiometricManagerImpl @Inject constructor(
    private val biometricRepository: BiometricRepository,
    private val remoteConfigManager: RemoteConfigManager
) : BiometricManager {

    override suspend fun setBiometric(use: Boolean) {
        biometricRepository.setBiometric(use)
    }

    override suspend fun isBiometricEnabled(): Boolean {
        val useBiometricEnabled = remoteConfigManager.getBoolean(RemoteConfigKey.UseBiometric)
        val isBiometricSet = biometricRepository.biometricIsSet.firstOrNull().orFalse
        return useBiometricEnabled && isBiometricSet
    }
}