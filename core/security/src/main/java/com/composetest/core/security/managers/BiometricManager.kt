package com.composetest.core.security.managers

interface BiometricManager {
    suspend fun setBiometric(use: Boolean)
    suspend fun biometricIsEnabled(): Boolean
}