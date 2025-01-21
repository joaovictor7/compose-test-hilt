package com.composetest.core.domain.repositories

interface DatabaseRepository {
    suspend fun getSqliteSecretKey(): String?

    suspend fun setSqliteSecretKey(secretKey: String)
}