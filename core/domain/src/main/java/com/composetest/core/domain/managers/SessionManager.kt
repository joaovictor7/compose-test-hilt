package com.composetest.core.domain.managers

import com.composetest.core.domain.models.session.AuthenticationModel

interface SessionManager {

    suspend fun createSession(authenticationModel: AuthenticationModel)

    suspend fun needsLogin(): Boolean

    suspend fun isSessionValid(): Boolean

    suspend fun finishCurrentSession()
}