package com.composetest.core.domain.repository

interface DatabaseRepository {
    suspend fun getSqliteSecretKey(): String?

    suspend fun setSqliteSecretKey(secretKey: String)
}