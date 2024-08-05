package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.data.network.requests.AuthenticationRequest
import com.composetest.core.data.data.network.responses.AuthenticationResponse

interface AuthenticationRepository {
    suspend fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ): T
}