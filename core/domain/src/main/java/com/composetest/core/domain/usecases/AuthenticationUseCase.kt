package com.composetest.core.domain.usecases

import com.composetest.common.throwables.UnauthorizedRequestThrowable
import com.composetest.core.data.data.repositories.remote.AuthenticationRepository
import com.composetest.core.data.data.network.requests.AuthenticationRequest
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.mappers.SessionModelMapper
import com.composetest.core.domain.throwables.InvalidCredentialsThrowable
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val sessionManager: SessionManager,
    private val sessionModelMapper: SessionModelMapper
) {

    suspend operator fun invoke(email: String, password: String) {
        val response = runCatching {
            authenticationRepository.authentication(AuthenticationRequest(email, password))
        }.getOrElse {
            when (it) {
                is UnauthorizedRequestThrowable -> throw InvalidCredentialsThrowable()
                else -> throw it
            }
        }
        val sessionWithUser = sessionModelMapper(response)
        sessionManager.createSession(sessionWithUser)
    }
}