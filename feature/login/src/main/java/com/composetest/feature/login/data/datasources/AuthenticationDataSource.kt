package com.composetest.feature.login.data.datasources

import com.composetest.feature.login.network.responses.AuthenticationResponse

internal interface AuthenticationDataSource {
    suspend fun authentication(email: String, password: String): AuthenticationResponse
    suspend fun updateUserNameAndEmail(displayName: String?, email: String)
    suspend fun updateUserPassword(password: String)
}