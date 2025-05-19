package com.composetest.feature.login.data.datasource

import com.composetest.feature.login.network.response.AuthenticationResponse

internal interface AuthenticationDataSource {
    suspend fun authentication(email: String, password: String): AuthenticationResponse
    suspend fun updateUserNameAndEmail(displayName: String?, email: String)
    suspend fun updateUserPassword(password: String)
}