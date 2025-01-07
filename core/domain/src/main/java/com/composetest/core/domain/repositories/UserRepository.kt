package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.UserModel

interface UserRepository {
    suspend fun upsert(user: UserModel)
    suspend fun getCurrentUser(): UserModel?
    suspend fun getLastUser(): UserModel?
}