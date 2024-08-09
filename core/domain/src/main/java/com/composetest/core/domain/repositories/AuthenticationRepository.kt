package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.models.session.AuthenticationModel

interface AuthenticationRepository {
    suspend fun authentication(
        authenticationCredentialsModel: AuthenticationCredentialsModel
    ): AuthenticationModel
}