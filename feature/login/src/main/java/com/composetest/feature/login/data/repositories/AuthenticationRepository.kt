package com.composetest.feature.login.data.repositories

import com.composetest.common.errors.ApiError
import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.core.security.providers.CipherProvider
import com.composetest.feature.login.data.datasources.AuthenticationDataSource
import com.composetest.feature.login.data.mappers.AuthenticationMapper
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject

internal class AuthenticationRepository @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource,
    private val authenticationMapper: AuthenticationMapper,
    private val cipherProvider: CipherProvider,
) {

    suspend fun authentication(email: String, encryptedPassword: String) = runCatching {
        val response = apiErrorHandler {
            authenticationDataSource.authentication(
                email,
                cipherProvider.decrypt(encryptedPassword),
            )
        }
        authenticationMapper.mapperToModel(response, encryptedPassword)
    }.getOrElse {
        throw when (it.cause) {
            is FirebaseAuthInvalidCredentialsException -> ApiError.Unauthorized()
            is FirebaseNetworkException -> ApiError.Network()
            else -> it
        }
    }
}