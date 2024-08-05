package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.data.network.requests.AuthenticationRequest
import com.composetest.core.data.data.network.responses.AuthenticationResponse
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override suspend fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = mapper(authenticationDataSource.authentication(request))
}