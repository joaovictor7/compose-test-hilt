package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.session.AuthenticationModel

interface AuthenticationRepository {
    suspend fun authentication(email: String, encryptedPassword: String): AuthenticationModel
    suspend fun updateUserNameAndEmail(name: String, email: String)
    suspend fun updatePassword(password: String)
}