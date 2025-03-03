package com.composetest.core.domain.mappers

import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.domain.models.session.SessionModel
import javax.inject.Inject

internal class SessionMapper @Inject constructor() {

    fun mapperToModel(authenticationModel: AuthenticationModel, sessionDuration: Long) =
        SessionModel(
            token = authenticationModel.sessionToken,
            startDate = authenticationModel.sessionStartDateTime,
            endDate = authenticationModel.sessionStartDateTime.plusSeconds(sessionDuration),
        )
}