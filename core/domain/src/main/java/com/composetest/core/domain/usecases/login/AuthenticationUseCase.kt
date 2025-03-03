package com.composetest.core.domain.usecases.login

import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.mappers.SessionMapper
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class AuthenticationUseCase @Inject internal constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val sessionMapper: SessionMapper,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(email: String, encryptedPassword: String) {
        val authenticationModel = authenticationRepository.authentication(email, encryptedPassword)
        val session = sessionMapper.mapperToModel(authenticationModel, SESSION_WEEKS_DURATION)
        userRepository.upsert(authenticationModel.user)
        sessionManager.createSession(session, authenticationModel.user)
    }

    private companion object {
        const val SESSION_WEEKS_DURATION = 2L
    }
}