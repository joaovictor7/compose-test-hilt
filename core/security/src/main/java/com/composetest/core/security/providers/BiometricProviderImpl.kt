package com.composetest.core.security.providers

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import com.composetest.core.domain.usecases.remoteconfigs.GetBooleanRemoteConfigUseCase
import com.composetest.core.security.enums.BiometricRemoteConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class BiometricProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getBooleanRemoteConfigUseCase: GetBooleanRemoteConfigUseCase,
) : BiometricProvider {

    override val biometricIsAvailable: Boolean
        get() = BiometricManager.from(context)
            .canAuthenticate(BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS &&
                getBooleanRemoteConfigUseCase(BiometricRemoteConfig.USE_BIOMETRIC)
}