package com.composetest.domain.usecase

import com.composetest.core.domain.repository.SessionRepository
import javax.inject.Inject

internal class CheckNeedsLoginUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() = sessionRepository.getCurrentSession() == null
}