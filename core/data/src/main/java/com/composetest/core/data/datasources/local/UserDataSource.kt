package com.composetest.core.data.datasources.local

import com.composetest.core.database.entities.UserEntity

internal interface UserDataSource {
    suspend fun upsert(user: UserEntity)
    suspend fun getCurrentUser(): UserEntity?
}