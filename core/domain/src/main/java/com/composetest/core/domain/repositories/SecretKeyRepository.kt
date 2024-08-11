package com.composetest.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SecretKeyRepository {
    suspend fun getSqliteSecretKey(): Flow<String?>

    suspend fun setSqliteSecretKey(secretKey: String)
}