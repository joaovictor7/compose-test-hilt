package com.composetest.core.data.data.datasources.remote

import com.composetest.core.data.extensions.post
import com.composetest.core.data.managers.RemoteCallManager
import com.composetest.core.data.data.network.requests.AuthenticationRequest
import com.composetest.core.data.data.network.responses.AuthenticationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody

internal class AuthenticationDataSourceImpl(
    private val bffApi: HttpClient,
    private val safeRemoteCallManager: RemoteCallManager
) : AuthenticationDataSource {

    override suspend fun authentication(authenticationRequest: AuthenticationRequest) =
        safeRemoteCallManager.safeRemoteCall {
            bffApi.post<AuthenticationResponse>("authenticate") {
                setBody(authenticationRequest)
            }
        }
}