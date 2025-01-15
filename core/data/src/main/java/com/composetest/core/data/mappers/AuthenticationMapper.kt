package com.composetest.core.data.mappers

import com.composetest.common.providers.DateTimeProvider
import com.composetest.common.utils.convertFromSeconds
import com.composetest.core.data.api.responses.AuthenticationResponse
import com.composetest.core.domain.models.session.AuthenticationModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import java.time.LocalDateTime
import javax.inject.Inject

internal class AuthenticationMapper @Inject constructor(
    private val dateTimeProvider: DateTimeProvider,
    private val userMapper: UserMapper
) {

    operator fun invoke(
        firebaseUser: FirebaseUser?,
        tokenResult: GetTokenResult?,
    ) = AuthenticationResponse(
        sessionToken = tokenResult?.token.orEmpty(),
        sessionStartDateTime = tokenResult.formatDateTime(),
        userId = firebaseUser?.uid.orEmpty(),
        userEmail = firebaseUser?.email.orEmpty(),
        userName = firebaseUser?.displayName,
    )

    operator fun invoke(
        authenticationResponse: AuthenticationResponse,
        password: String
    ) = AuthenticationModel(
        sessionToken = authenticationResponse.sessionToken,
        sessionStartDateTime = LocalDateTime.parse(authenticationResponse.sessionStartDateTime),
        user = userMapper(authenticationResponse, password)
    )

    private fun GetTokenResult?.formatDateTime() =
        this?.authTimestamp?.let(::convertFromSeconds)?.toString()
            ?: dateTimeProvider.currentDateTime.toString()
}