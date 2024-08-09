package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.UserModel

interface UserRepository {
    suspend fun insert(user: UserModel)
    suspend fun getCurrentUser(): UserModel?
}