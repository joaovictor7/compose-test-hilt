package com.composetest.core.domain.usecases

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.managers.BiometricRepository
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.providers.BiometricProvider
import com.composetest.core.domain.remoteconfigs.RemoteConfigKey
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class BiometricIsAvailableUseCase @Inject constructor(
    private val biometricRepository: BiometricRepository,
    private val biometricProvider: BiometricProvider,
    private val remoteConfigManager: RemoteConfigManager
) {
    suspend operator fun invoke() =
        remoteConfigManager.getBoolean(RemoteConfigKey.UseBiometric) &&
                biometricProvider.isBiometricAvailable &&
                biometricRepository.biometricIsSet.firstOrNull().orFalse
}