package com.composetest.core.data.managers

import com.composetest.core.data.repositories.SessionRepository
import com.composetest.core.data.workmanager.workes.SessionWorker
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

internal class SessionManagerImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val workManager: WorkManager
) : SessionManager {

    override suspend fun createSession(session: SessionModel, user: UserModel) {
        sessionRepository.insert(session, user)
        sessionScheduling(session.startDate, session.endDate)
    }

    override suspend fun needsLogin() = sessionRepository.getCurrentSession() == null

    override suspend fun sessionIsLogged() =
        sessionRepository.getCurrentSession()?.isActive ?: false

    override suspend fun finishCurrentSession() {
        sessionRepository.getCurrentSession()?.let {
            sessionRepository.finishSession(it.id)
        }
    }

    private fun sessionScheduling(startDate: LocalDateTime, endDate: LocalDateTime) {
        val duration = Duration.between(startDate, endDate)
        workManager.enqueueOneTimeWork(SessionWorker.Builder(duration))
    }
}