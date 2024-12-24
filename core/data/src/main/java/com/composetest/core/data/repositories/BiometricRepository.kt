package com.composetest.core.data.repositories

import kotlinx.coroutines.flow.Flow

internal interface BiometricRepository {
    val biometricIsSet: Flow<Boolean?>

    suspend fun setBiometric(use: Boolean)
}