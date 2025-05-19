package com.composetest.core.domain.usecase.session

import com.composetest.common.extension.orFalse
import com.composetest.core.domain.repository.SessionRepository
import javax.inject.Inject

class CheckSessionIsValidUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() = sessionRepository.getCurrentSession()?.isActive.orFalse
}