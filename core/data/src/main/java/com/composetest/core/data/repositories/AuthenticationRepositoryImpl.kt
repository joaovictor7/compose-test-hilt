package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.domain.errors.ApiError
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource,
    private val authenticationMapper: AuthenticationMapper
) : AuthenticationRepository {

    override suspend fun authentication(email: String, password: String) = runCatching {
        val response = authenticationDataSource.authentication(email, password)
        authenticationMapper(response, password)
    }.getOrElse {
        throw when (it.cause) {
            is FirebaseAuthInvalidCredentialsException -> ApiError.Unauthorized()
            is FirebaseNetworkException -> ApiError.Network()
            else -> it
        }
    }
}