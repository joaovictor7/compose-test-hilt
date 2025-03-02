package com.composetest.core.data.mappers

import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.SessionModel
import javax.inject.Inject

internal class SessionMapper @Inject constructor() {

    fun mapperToModel(entity: SessionEntity?) = entity?.let {
        SessionModel(
            id = it.id,
            token = it.token,
            startDate = it.startDate,
            endDate = it.endDate,
            isActive = it.isActive
        )
    }

    fun mapperToEntity(session: SessionModel, user: UserModel) = SessionEntity(
        token = session.token,
        startDate = session.startDate,
        endDate = session.endDate,
        isActive = session.isActive,
        userId = user.id,
    )
}