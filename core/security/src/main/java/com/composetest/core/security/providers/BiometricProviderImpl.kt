package com.composetest.core.security.providers

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import com.composetest.core.domain.providers.BiometricProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class BiometricProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : BiometricProvider {

    override val isBiometricAvailable: Boolean
        get() = BiometricManager.from(context)
            .canAuthenticate(BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
}