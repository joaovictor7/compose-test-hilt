package com.composetest.core.security.utils

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.composetest.core.security.params.BiometricTextParam

fun showBiometricPrompt(
    context: Context,
    biometricTexts: BiometricTextParam,
    confirmationRequired: Boolean = false,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
    onError: (code: Int, message: String) -> Unit,
) {
    val fragmentActivity = context as? FragmentActivity ?: return
    val biometricPrompt = BiometricPrompt(
        fragmentActivity,
        ContextCompat.getMainExecutor(fragmentActivity),
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                onFailure()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onError(errorCode, errString.toString())
            }
        }
    )
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(biometricTexts.title)
        .setSubtitle(biometricTexts.subtitle)
        .setDescription(biometricTexts.description)
        .setNegativeButtonText(biometricTexts.negativeButtonText)
        .setConfirmationRequired(confirmationRequired)
        .build()

    biometricPrompt.authenticate(promptInfo)
}