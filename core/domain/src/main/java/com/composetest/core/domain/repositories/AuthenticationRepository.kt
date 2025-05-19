package com.composetest.core.domain.repositories

interface AuthenticationRepository {
    fun updateUserNameAndEmail(name: String, email: String)
}