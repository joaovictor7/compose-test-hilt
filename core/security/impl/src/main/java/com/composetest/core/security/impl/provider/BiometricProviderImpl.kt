package com.composetest.core.security.impl.provider

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import com.composetest.core.domain.usecase.remoteconfig.GetBooleanRemoteConfigUseCase
import com.composetest.core.security.api.provider.BiometricProvider
import com.composetest.core.security.impl.enums.BiometricRemoteConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class BiometricProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getBooleanRemoteConfigUseCase: GetBooleanRemoteConfigUseCase,
) : BiometricProvider {

    override val biometricIsAvailable: Boolean
        get() = BiometricManager.from(context)
            .canAuthenticate(BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS &&
                getBooleanRemoteConfigUseCase(BiometricRemoteConfig.USE_BIOMETRIC)
}