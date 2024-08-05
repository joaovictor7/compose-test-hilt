package com.composetest.core.data.data.datasources.remote

import com.composetest.core.data.data.network.requests.AuthenticationRequest
import com.composetest.core.data.data.network.responses.AuthenticationResponse

internal interface AuthenticationDataSource {

    suspend fun authentication(
        authenticationRequest: AuthenticationRequest
    ): AuthenticationResponse
}