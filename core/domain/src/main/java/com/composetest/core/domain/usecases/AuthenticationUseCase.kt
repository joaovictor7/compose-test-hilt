package com.composetest.core.domain.usecases

import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.throwables.InvalidCredentialsThrowable
import com.composetest.core.domain.throwables.network.UnauthorizedRequestThrowable
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val sessionManager: SessionManager
) {

    suspend operator fun invoke(authenticationCredentialsModel: AuthenticationCredentialsModel) {
        val authenticationModel = runCatching {
            authenticationRepository.authentication(authenticationCredentialsModel)
        }.getOrElse {
            when (it) {
                is UnauthorizedRequestThrowable -> throw InvalidCredentialsThrowable()
                else -> throw it
            }
        }
        sessionManager.createSession(authenticationModel.session, authenticationModel.user)
    }
}