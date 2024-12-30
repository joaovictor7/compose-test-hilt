package com.composetest.core.domain.managers

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel

interface SessionManager {

    suspend fun createSession(session: SessionModel, user: UserModel)

    suspend fun needsLogin(): Boolean

    suspend fun isSessionValid(): Boolean

    suspend fun finishCurrentSession()
}