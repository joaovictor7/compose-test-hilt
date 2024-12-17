package com.composetest.core.data.managers

import com.composetest.core.data.providers.WorkManagerProvider
import com.composetest.core.data.workmanagers.workes.SessionWorker
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.domain.models.session.SessionModel
import com.composetest.core.domain.repositories.SessionRepository
import com.composetest.core.domain.repositories.UserRepository
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

internal class SessionManagerImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val workManagerProvider: WorkManagerProvider
) : SessionManager {

    override suspend fun createSession(authenticationModel: AuthenticationModel) {
        val session = SessionModel(
            token = authenticationModel.sessionToken,
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now().plusWeeks(2),
            isFinished = false
        )
        userRepository.upsert(authenticationModel.user)
        sessionRepository.insert(session, authenticationModel.user)
        createSessionSchedule(session.startDate, session.endDate)
    }

    override suspend fun needsLogin() = sessionRepository.getCurrentSession() == null

    override suspend fun isSessionValid(): Boolean {
        val currentSession = sessionRepository.getCurrentSession() ?: return false
        return !currentSession.isFinished
    }

    private fun createSessionSchedule(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ) {
        val duration = Duration.between(startDate, endDate)
        workManagerProvider.createOneTimeWork(SessionWorker.Builder(duration))
    }
}