package com.composetest.core.domain.mappers

import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.domain.models.session.SessionModel
import javax.inject.Inject

internal class SessionMapper @Inject constructor() {
    operator fun invoke(authenticationModel: AuthenticationModel) = SessionModel(
        token = authenticationModel.sessionToken,
        startDate = authenticationModel.sessionStartDateTime,
        endDate = authenticationModel.sessionStartDateTime.plusWeeks(SESSION_WEEKS_DURATION),
        isFinished = false
    )

    private companion object {
        const val SESSION_WEEKS_DURATION = 2L
    }
}