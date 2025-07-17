package com.composetest.core.domain.repository

interface AuthenticationRepository {
    fun updateUserNameAndEmail(name: String, email: String)
}