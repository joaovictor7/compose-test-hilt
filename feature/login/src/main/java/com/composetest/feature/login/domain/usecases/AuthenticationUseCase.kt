package com.composetest.feature.login.domain.usecases

import com.composetest.feature.login.domain.mappers.SessionMapper
import com.composetest.core.domain.repositories.UserRepository
import com.composetest.core.domain.usecases.session.CreateSessionUseCase
import com.composetest.feature.login.data.repositories.AuthenticationRepository
import javax.inject.Inject

internal class AuthenticationUseCase @Inject internal constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val sessionMapper: SessionMapper,
    private val createSessionUseCase: CreateSessionUseCase
) {

    suspend operator fun invoke(email: String, encryptedPassword: String) {
        val authenticationModel = authenticationRepository.authentication(email, encryptedPassword)
        val session = sessionMapper.mapperToModel(authenticationModel, SESSION_WEEKS_DURATION)
        userRepository.upsert(authenticationModel.user)
        createSessionUseCase(session, authenticationModel.user)
    }

    private companion object {
        const val SESSION_WEEKS_DURATION = 2L
    }
}