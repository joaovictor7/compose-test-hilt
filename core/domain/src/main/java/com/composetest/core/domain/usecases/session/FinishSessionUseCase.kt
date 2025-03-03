package com.composetest.core.domain.usecases.session

import com.composetest.core.domain.repositories.SessionRepository
import javax.inject.Inject

class FinishSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        sessionRepository.getCurrentSession()?.let {
            sessionRepository.finishSession(it.id)
        }
    }
}