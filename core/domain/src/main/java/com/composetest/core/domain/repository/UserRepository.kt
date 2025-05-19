package com.composetest.core.domain.repository

import com.composetest.core.domain.model.UserModel

interface UserRepository {
    suspend fun upsert(user: UserModel)
    suspend fun getCurrentUser(): UserModel?
    suspend fun getLastActiveUser(): UserModel?
}