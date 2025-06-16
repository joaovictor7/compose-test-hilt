package com.composetest.feature.login.data.repository

import com.composetest.core.domain.repository.AuthenticationRepository
import com.composetest.core.network.model.ApiError
import com.composetest.core.network.util.apiErrorHandler
import com.composetest.core.security.provider.CipherProvider
import com.composetest.feature.login.data.datasource.AuthenticationDataSource
import com.composetest.feature.login.data.mapper.AuthenticationMapper
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource,
    private val authenticationMapper: AuthenticationMapper,
    private val cipherProvider: CipherProvider,
) : AuthenticationRepository {

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

    override fun updateUserNameAndEmail(name: String, email: String) {
        // TODO: Not yet implemented
    }
}