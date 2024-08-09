package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel

interface SessionRepository {
    suspend fun insert(session: SessionModel, user: UserModel)
    suspend fun finishSession(sessionId: Long)
    suspend fun getCurrentSession(): SessionModel?
}