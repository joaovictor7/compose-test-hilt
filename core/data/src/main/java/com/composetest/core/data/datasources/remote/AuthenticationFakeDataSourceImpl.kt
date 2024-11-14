package com.composetest.core.data.datasources.remote

import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.network.responses.SessionResponse
import com.composetest.core.data.network.responses.UserResponse
import com.composetest.core.data.utils.RemoteCallUtils
import java.time.LocalDateTime

internal class AuthenticationFakeDataSourceImpl(
    private val remoteCallUtils: RemoteCallUtils
) : AuthenticationDataSource {

    override suspend fun authentication(request: AuthenticationRequest) =
        remoteCallUtils.executeRemoteCall {
            val sessionStartDate = LocalDateTime.now()
            val sessionEndDate = sessionStartDate.plusMinutes(PLUS_FAKE_SESSION_DURATION)
            AuthenticationResponse(
                user = UserResponse(
                    id = "123",
                    name = "Teste",
                    email = "teste@teste.com"
                ),
                sessionResponse = SessionResponse(
                    token = "43reddcdsfe434323cdf3434",
                    startDate = sessionStartDate.toString(),
                    endDate = sessionEndDate.toString(),
                )
            )
        }

    private companion object {
        const val PLUS_FAKE_SESSION_DURATION = 50L
    }
}