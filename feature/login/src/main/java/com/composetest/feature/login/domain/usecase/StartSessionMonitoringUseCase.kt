package com.composetest.feature.login.domain.usecase

import com.composetest.core.domain.model.SessionModel
import com.composetest.feature.login.data.repository.SessionRepositoryImpl
import java.time.Duration
import javax.inject.Inject

internal class StartSessionMonitoringUseCase @Inject constructor(
    private val sessionRepository: SessionRepositoryImpl,
) {

    operator fun invoke(session: SessionModel) {
        val duration = Duration.between(session.startDate, session.endDate)
        sessionRepository.enqueueMonitoringSessionWorker(duration)
    }
}