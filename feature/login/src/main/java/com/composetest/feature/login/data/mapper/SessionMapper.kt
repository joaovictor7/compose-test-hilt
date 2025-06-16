package com.composetest.feature.login.data.mapper

import com.composetest.core.database.data.entity.SessionEntity
import com.composetest.core.domain.model.SessionModel
import com.composetest.core.domain.model.UserModel
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