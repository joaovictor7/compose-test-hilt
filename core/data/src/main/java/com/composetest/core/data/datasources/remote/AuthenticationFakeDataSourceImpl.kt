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
            AuthenticationModel(
                sessionToken = "43reddcdsfe434323cdf3434",
                sessionStartDateTime = LocalDateTime.now(),
                user = UserModel(
                    id = "123",
                    name = "Teste",
                    email = "teste@teste.com"
                )
            )
        }

    override suspend fun updateUserProfile() {
        TODO("Not yet implemented")
    }
}