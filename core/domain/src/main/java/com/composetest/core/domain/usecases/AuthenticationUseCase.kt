package com.composetest.core.domain.usecases

import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.repositories.AuthenticationRepository
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val sessionManager: SessionManager
) {

    suspend operator fun invoke(authenticationCredentialsModel: AuthenticationCredentialsModel) {
        val authenticationModel = authenticationRepository
            .authentication(authenticationCredentialsModel)
        sessionManager.createSession(authenticationModel)
    }
}