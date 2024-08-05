package com.composetest.core.domain.managers

import com.composetest.core.data.data.repositories.local.WorkManagerRepository
import com.composetest.core.data.workmanagers.workes.SessionWorker
import com.composetest.core.data.data.repositories.local.SessionRepository
import com.composetest.core.data.data.repositories.local.UserRepository
import com.composetest.core.domain.mappers.SessionEntityMapper
import com.composetest.core.domain.mappers.SessionModelMapper
import com.composetest.core.domain.mappers.UserEntityMapper
import com.composetest.core.domain.models.session.SessionWithUserModel
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

internal class SessionManagerImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val sessionModelMapper: SessionModelMapper,
    private val sessionEntityMapper: SessionEntityMapper,
    private val userEntityMapper: UserEntityMapper,
    private val workManagerRepository: WorkManagerRepository
) : SessionManager {

    override suspend fun createSession(sessionWithUser: SessionWithUserModel) {
        userRepository.insert(userEntityMapper(sessionWithUser.user))
        sessionRepository.insert(sessionEntityMapper(sessionWithUser))
        createSessionSchedule(sessionWithUser.startDate, sessionWithUser.endDate)
    }

    override suspend fun needsLogin() = sessionRepository.getCurrentSession() == null

    override suspend fun isSessionValid(): Boolean {
        val currentSession = sessionRepository.getCurrentSession()
            ?.let(sessionModelMapper::invoke)
            ?: return false
        return !currentSession.isFinished
    }

    private fun createSessionSchedule(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ) {
        val duration = Duration.between(startDate, endDate)
        workManagerRepository.createOneTimeWork(SessionWorker.Builder(duration))
    }
}