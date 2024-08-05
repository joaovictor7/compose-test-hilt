package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.data.network.requests.AuthenticationRequest
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override suspend fun authentication(request: AuthenticationRequest) =
        authenticationDataSource.authentication(request)
}