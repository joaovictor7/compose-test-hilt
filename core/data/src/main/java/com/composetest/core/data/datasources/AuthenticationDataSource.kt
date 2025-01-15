package com.composetest.core.data.datasources

import com.composetest.core.data.api.responses.AuthenticationResponse

internal interface AuthenticationDataSource {

    suspend fun authentication(email: String, password: String): AuthenticationResponse

    suspend fun updateUserProfile()
}