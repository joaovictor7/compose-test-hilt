package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.domain.repositories.AuthenticationRepository
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource,
    private val authenticationMapper: AuthenticationMapper
) : AuthenticationRepository {

    override suspend fun authentication(
        authenticationCredentialsModel: AuthenticationCredentialsModel
    ): AuthenticationModel {
        val request = authenticationMapper(authenticationCredentialsModel)
        val response = authenticationDataSource.authentication(request)
        return authenticationMapper(response)
    }
}