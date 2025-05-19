package com.composetest.core.domain.repository

import com.composetest.core.domain.model.UserModel
import com.composetest.core.domain.model.session.SessionModel

interface SessionRepository {
    suspend fun insert(session: SessionModel, user: UserModel)
    suspend fun finishSession(id: Long)
    suspend fun getCurrentSession(): SessionModel?
}