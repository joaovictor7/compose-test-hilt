package com.composetest.core.data.datasources.local

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.api.responses.AuthenticationResponse
import com.composetest.core.data.datasources.AuthenticationDataSource
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils

internal class AuthenticationFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val dateTimeProvider: DateTimeProvider,
    private val assetsProvider: AssetsProvider,
) : AuthenticationDataSource {

    override suspend fun authentication(email: String, password: String) =
        apiCallUtils.executeApiCall {
            assetsProvider.getObjectFromJson<AuthenticationResponse>("authentication")
                .copy(sessionStartDateTime = dateTimeProvider.currentDateTime.toString())
        }

    override suspend fun updateUserProfile() {
        TODO("Not yet implemented")
    }
}