package com.composetest.core.data.mappers

import com.composetest.core.data.api.requests.AuthenticationRequest
import com.composetest.core.data.api.responses.AuthenticationResponse
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.domain.models.session.SessionModel
import java.time.LocalDateTime
import javax.inject.Inject

internal class AuthenticationMapper @Inject constructor() {

    operator fun invoke(response: AuthenticationResponse) = AuthenticationModel(
        session = SessionModel(
            token = response.sessionResponse.token,
            startDate = LocalDateTime.parse(response.sessionResponse.startDate),
            endDate = LocalDateTime.parse(response.sessionResponse.endDate),
            isFinished = false
        ),
        user = UserModel(
            id = response.user.id,
            email = response.user.email,
            name = response.user.name
        )
    )

    operator fun invoke(model: AuthenticationCredentialsModel) = AuthenticationRequest(
        email = model.email,
        password = model.password
    )
}