package com.composetest.core.data.datasources.remote

import com.composetest.core.data.api.requests.AuthenticationRequest
import com.composetest.core.data.api.responses.AuthenticationResponse

internal interface AuthenticationDataSource {

    suspend fun authentication(request: AuthenticationRequest): AuthenticationResponse
}