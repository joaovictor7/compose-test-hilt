package com.composetest.core.security.enums

import androidx.biometric.BiometricPrompt

enum class BiometricError(val code: Int) {
    HW_UNAVAILABLE(BiometricPrompt.ERROR_HW_UNAVAILABLE),
    UNABLE_TO_PROCESS(BiometricPrompt.ERROR_UNABLE_TO_PROCESS),
    TIMEOUT(BiometricPrompt.ERROR_TIMEOUT),
    NO_SPACE(BiometricPrompt.ERROR_NO_SPACE),
    CANCELED(BiometricPrompt.ERROR_CANCELED),
    LOCKOUT(BiometricPrompt.ERROR_LOCKOUT),
    VENDOR(BiometricPrompt.ERROR_LOCKOUT),
    LOCKOUT_PERMANENT(BiometricPrompt.ERROR_LOCKOUT_PERMANENT),
    USER_CANCELED(BiometricPrompt.ERROR_USER_CANCELED),
    NO_BIOMETRICS(BiometricPrompt.ERROR_NO_BIOMETRICS),
    HW_NOT_PRESENT(BiometricPrompt.ERROR_HW_NOT_PRESENT),
    NEGATIVE_BUTTON(BiometricPrompt.ERROR_NEGATIVE_BUTTON),
    NO_DEVICE_CREDENTIAL(BiometricPrompt.ERROR_NO_DEVICE_CREDENTIAL),
    SECURITY_UPDATE_REQUIRED(BiometricPrompt.ERROR_SECURITY_UPDATE_REQUIRED),
    CONTENT_VIEW_MORE_OPTIONS_BUTTON(BiometricPrompt.ERROR_CONTENT_VIEW_MORE_OPTIONS_BUTTON),
    UNKNOWN(-1);

    companion object {
        val BiometricError.userClosedPrompt get() = this in listOf(NEGATIVE_BUTTON, USER_CANCELED)
        val BiometricError.biometricIsLockout get() = this in listOf(LOCKOUT, LOCKOUT_PERMANENT)
        fun getErrorByCode(errorCode: Int) = entries.firstOrNull { it.code == errorCode } ?: UNKNOWN
    }
}