package com.composetest.feature.login.domain.usecase

import com.composetest.feature.login.data.repository.SessionRepositoryImpl
import com.composetest.feature.login.domain.mapper.SessionMapper
import com.composetest.feature.login.domain.model.AuthenticationModel
import javax.inject.Inject

internal class CreateSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepositoryImpl,
    private val createUserUseCase: CreateUserUseCase,
    private val startSessionMonitoringUseCase: StartSessionMonitoringUseCase,
    private val sessionMapper: SessionMapper,
) {
    suspend operator fun invoke(authenticationModel: AuthenticationModel) {
        val session = sessionMapper.mapperToModel(authenticationModel, SESSION_WEEKS_DURATION)
        createUserUseCase(authenticationModel.user)
        sessionRepository.insert(session, authenticationModel.user)
        startSessionMonitoringUseCase(session)
    }

    private companion object {
        const val SESSION_WEEKS_DURATION = 2L
    }
}