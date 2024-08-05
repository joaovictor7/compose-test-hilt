package com.composetest.core.domain.mappers

import com.composetest.core.data.data.network.responses.AuthenticationResponse
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.domain.models.session.SessionModel
import com.composetest.core.domain.models.session.SessionWithUserModel
import java.time.LocalDateTime
import javax.inject.Inject

class SessionModelMapper @Inject constructor(private val userModelMapper: UserModelMapper) {

    operator fun invoke(response: AuthenticationResponse) = SessionWithUserModel(
        token = response.sessionResponse.token,
        startDate = LocalDateTime.parse(response.sessionResponse.startDate),
        endDate = LocalDateTime.parse(response.sessionResponse.endDate),
        isFinished = false,
        user = userModelMapper(response.user)
    )

    operator fun invoke(entity: SessionEntity?) = entity?.let {
        SessionModel(
            id = it.id,
            token = it.token,
            initialDate = it.startDate,
            endDate = it.endDate,
            isFinished = it.isFinished
        )
    }
}