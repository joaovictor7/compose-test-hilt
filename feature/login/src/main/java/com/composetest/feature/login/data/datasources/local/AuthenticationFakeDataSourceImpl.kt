package com.composetest.feature.login.data.datasources.local

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.extensions.readJsonAs
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.feature.login.data.datasources.AuthenticationDataSource
import com.composetest.feature.login.network.responses.AuthenticationResponse

internal class AuthenticationFakeDataSourceImpl(
    private val apiCallUtils: ApiCallUtils,
    private val dateTimeProvider: DateTimeProvider,
    private val assetsProvider: AssetsProvider,
) : AuthenticationDataSource {

    override suspend fun authentication(email: String, password: String) =
        apiCallUtils.executeApiCall {
            assetsProvider.readJsonAs<AuthenticationResponse>("authentication")
                .copy(sessionStartDateTime = dateTimeProvider.currentDateTime.toString())
        }

    override suspend fun updateUserNameAndEmail(displayName: String?, email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserPassword(password: String) {
        TODO("Not yet implemented")
    }
}