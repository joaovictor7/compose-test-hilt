package com.composetest.core.data.data.datasources.remote

import com.composetest.core.data.extensions.post
import com.composetest.core.data.managers.RemoteCallManager
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody

internal class AuthenticationDataSourceImpl(
    private val httpClient: HttpClient,
    private val safeRemoteCallManager: RemoteCallManager
) : AuthenticationDataSource {

    override suspend fun authentication(authenticationRequest: AuthenticationRequest) =
        safeRemoteCallManager.safeRemoteCall {
            httpClient.post<AuthenticationResponse>("authenticate") {
                setBody(authenticationRequest)
            }
        }
}