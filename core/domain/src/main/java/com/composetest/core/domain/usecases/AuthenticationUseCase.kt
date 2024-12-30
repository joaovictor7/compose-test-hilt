package com.composetest.core.domain.usecases

import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.mappers.SessionMapper
import com.composetest.core.domain.mappers.UserMapper
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class AuthenticationUseCase @Inject internal constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val sessionMapper: SessionMapper,
    private val userMapper: UserMapper,
    private val sessionManager: SessionManager,
) {

    suspend operator fun invoke(authenticationCredentialsModel: AuthenticationCredentialsModel) {
        val authenticationModel =
            authenticationRepository.authentication(authenticationCredentialsModel)
        val user = userMapper(
            authenticationModel,
            authenticationModel.userEmail,
            authenticationCredentialsModel.password
        )
        val session = sessionMapper(authenticationModel)
        userRepository.upsert(user)
        sessionManager.createSession(session, user)
    }
}