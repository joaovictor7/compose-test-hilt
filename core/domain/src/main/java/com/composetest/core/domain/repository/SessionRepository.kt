package com.composetest.core.domain.repository

import com.composetest.core.domain.model.SessionModel

interface SessionRepository {
    suspend fun finishSession(id: Long)
    suspend fun getCurrentSession(): SessionModel?
}