package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.repositories.AuthenticationRepository
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override suspend fun authentication(
        authenticationCredentialsModel: AuthenticationCredentialsModel
    ) = authenticationDataSource.authentication(authenticationCredentialsModel)
}