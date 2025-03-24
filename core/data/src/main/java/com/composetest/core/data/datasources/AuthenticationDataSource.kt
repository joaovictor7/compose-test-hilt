package com.composetest.core.data.datasources

import com.composetest.core.network.responses.AuthenticationResponse

internal interface AuthenticationDataSource {
    suspend fun authentication(email: String, password: String): AuthenticationResponse
    suspend fun updateUserNameAndEmail(displayName: String?, email: String)
    suspend fun updateUserPassword(password: String)
}