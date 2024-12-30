package com.composetest.core.domain.managers

import kotlinx.coroutines.flow.Flow

interface BiometricRepository {
    val biometricIsSet: Flow<Boolean?>

    suspend fun setUseBiometric(useBiometric: Boolean)
}