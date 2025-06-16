package com.composetest.feature.login.domain.mapper

import com.composetest.core.domain.model.SessionModel
import com.composetest.feature.login.domain.model.AuthenticationModel
import javax.inject.Inject

internal class SessionMapper @Inject constructor() {

    fun mapperToModel(authenticationModel: AuthenticationModel, sessionDuration: Long) =
        SessionModel(
            token = authenticationModel.sessionToken,
            startDate = authenticationModel.sessionStartDateTime,
            endDate = authenticationModel.sessionStartDateTime.plusWeeks(sessionDuration),
        )
}