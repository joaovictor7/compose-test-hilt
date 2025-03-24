package com.composetest.core.data.repositories

import com.composetest.common.errors.ApiError
import com.composetest.core.data.datasources.AuthenticationDataSource
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.utils.apiErrorHandler
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.security.providers.CipherProvider
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource,
    private val authenticationMapper: AuthenticationMapper,
    private val cipherProvider: CipherProvider,
) : AuthenticationRepository {

    override suspend fun authentication(email: String, encryptedPassword: String) = runCatching {
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

    override suspend fun updateUserNameAndEmail(name: String, email: String) = apiErrorHandler {
        authenticationDataSource.updateUserNameAndEmail(name, email)
    }

    override suspend fun updatePassword(password: String) = apiErrorHandler {
        authenticationDataSource.updateUserPassword(cipherProvider.decrypt(password))
    }
}