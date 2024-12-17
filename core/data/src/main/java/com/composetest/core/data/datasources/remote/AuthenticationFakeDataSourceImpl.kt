package com.composetest.core.data.datasources.remote

import com.composetest.core.data.utils.RemoteCallUtils
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.AuthenticationModel
import java.time.LocalDateTime

internal class AuthenticationFakeDataSourceImpl(
    private val remoteCallUtils: RemoteCallUtils
) : AuthenticationDataSource {

    override suspend fun authentication(authenticationCredentials: AuthenticationCredentialsModel) =
        remoteCallUtils.executeRemoteCall {
            val sessionStartDate = LocalDateTime.now()
            val sessionEndDate = sessionStartDate.plusMinutes(PLUS_FAKE_SESSION_DURATION)
            AuthenticationModel(
                sessionToken = "43reddcdsfe434323cdf3434",
                user = UserModel(
                    id = "123",
                    name = "Teste",
                    email = "teste@teste.com"
                )
            )
        }

    private companion object {
        const val PLUS_FAKE_SESSION_DURATION = 50L
    }
}