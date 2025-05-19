package com.composetest.feature.login.domain.usecases

import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.domain.repositories.SessionRepository
import com.composetest.feature.login.domain.mappers.SessionMapper
import javax.inject.Inject

internal class CreateSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val createUserUseCase: CreateUserUseCase,
    private val sessionMapper: SessionMapper,
) {
    suspend operator fun invoke(authenticationModel: AuthenticationModel) {
        val session = sessionMapper.mapperToModel(authenticationModel, SESSION_WEEKS_DURATION)
        createUserUseCase(authenticationModel.user)
        sessionRepository.insert(session, authenticationModel.user)
    }

    private companion object {
        const val SESSION_WEEKS_DURATION = 2L
    }
}