package com.composetest.core.security.providers

import android.content.Context
import androidx.biometric.BiometricManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class BiometricsProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : BiometricsProvider {
    override val isStrongBiometricsAvailable: Boolean
        get() = BiometricManager.from(context)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
}