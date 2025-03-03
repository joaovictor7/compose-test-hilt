package com.composetest.core.domain.usecases.session

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel
import com.composetest.core.domain.repositories.SessionRepository
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(session: SessionModel, user: UserModel) {
        sessionRepository.insert(session, user)
    }
}