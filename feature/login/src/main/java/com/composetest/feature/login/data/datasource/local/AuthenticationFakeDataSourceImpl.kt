package com.composetest.feature.login.data.datasource.local

import com.composetest.common.provider.DateTimeProvider
import com.composetest.core.data.extension.readJsonAs
import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.login.data.datasource.AuthenticationDataSource
import com.composetest.feature.login.network.response.AuthenticationResponse

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