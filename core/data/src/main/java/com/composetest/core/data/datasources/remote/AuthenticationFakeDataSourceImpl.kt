package com.composetest.core.data.datasources.remote

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.api.responses.AuthenticationResponse
import com.composetest.core.data.utils.RemoteCallUtils

internal class AuthenticationFakeDataSourceImpl(
    private val remoteCallUtils: RemoteCallUtils,
    private val dateTimeProvider: DateTimeProvider,
) : AuthenticationDataSource {

    override suspend fun authentication(email: String, password: String) =
        remoteCallUtils.executeRemoteCall {
            AuthenticationResponse(
                sessionToken = "43reddcdsfe434323cdf3434",
                sessionStartDateTime = dateTimeProvider.currentDateTime,
                userId = "123",
                userEmail = "william.henry.harrison@example-pet-store.com",
                userName = "Teste",
            )
        }

    override suspend fun updateUserProfile() {
        TODO("Not yet implemented")
    }
}