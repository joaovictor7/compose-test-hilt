package com.composetest.core.security.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.composetest.core.security.enums.BiometricError
import com.composetest.core.ui.R

fun showBiometricPrompt(
    context: Context,
    @StringRes titleId: Int,
    @StringRes subtitleId: Int,
    @StringRes descriptionId: Int? = null,
    @StringRes negativeButtonTextId: Int? = null,
    confirmationRequired: Boolean = true,
    onSuccess: () -> Unit,
    onFailure: (() -> Unit)? = null,
    onError: ((error: BiometricError) -> Unit)? = null
) {
    showBiometricPrompt(
        context = context,
        title = context.getString(titleId),
        subtitle = context.getString(subtitleId),
        description = descriptionId?.let { context.getString(it) },
        negativeButtonText = negativeButtonTextId?.let { context.getString(it) },
        confirmationRequired = confirmationRequired,
        onSuccess = onSuccess,
        onFailure = onFailure,
        onError = onError
    )
}

fun showBiometricPrompt(
    context: Context,
    title: String,
    subtitle: String,
    description: String? = null,
    negativeButtonText: String? = null,
    confirmationRequired: Boolean = false,
    onSuccess: () -> Unit,
    onFailure: (() -> Unit)? = null,
    onError: ((error: BiometricError) -> Unit)? = null
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
                onFailure?.invoke()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onError?.invoke(BiometricError.getErrorByCode(errorCode))
            }
        }
    )
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(title)
        .setSubtitle(subtitle)
        .setDescription(description)
        .setNegativeButtonText(negativeButtonText ?: context.getString(R.string.close))
        .setConfirmationRequired(confirmationRequired)
        .build()

    biometricPrompt.authenticate(promptInfo)
}