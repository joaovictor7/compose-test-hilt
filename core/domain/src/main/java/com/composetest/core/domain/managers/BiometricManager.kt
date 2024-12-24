package com.composetest.core.domain.managers

interface BiometricManager {
    suspend fun setBiometric(use: Boolean)
    suspend fun isBiometricEnabled(): Boolean
}