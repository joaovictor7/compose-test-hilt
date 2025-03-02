package com.composetest.core.data.mappers

import com.composetest.common.extensions.convertedFromSeconds
import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.domain.models.session.AuthenticationModel
import com.composetest.core.network.responses.AuthenticationResponse
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import java.time.LocalDateTime
import javax.inject.Inject

internal class AuthenticationMapper @Inject constructor(
    private val dateTimeProvider: DateTimeProvider,
    private val userMapper: UserMapper
) {

    fun mapperToResponse(
        firebaseUser: FirebaseUser?,
        tokenResult: GetTokenResult?,
    ) = AuthenticationResponse(
        sessionToken = tokenResult?.token.orEmpty(),
        sessionStartDateTime = tokenResult.formatDateTime(),
        userId = firebaseUser?.uid.orEmpty(),
        userEmail = firebaseUser?.email.orEmpty(),
        userName = firebaseUser?.displayName,
    )

    fun mapperToModel(
        authenticationResponse: AuthenticationResponse,
        password: String
    ) = AuthenticationModel(
        sessionToken = authenticationResponse.sessionToken,
        sessionStartDateTime = LocalDateTime.parse(authenticationResponse.sessionStartDateTime),
        user = userMapper.mapperToModel(authenticationResponse, password)
    )

    private fun GetTokenResult?.formatDateTime() =
        this?.authTimestamp?.convertedFromSeconds?.toString()
            ?: dateTimeProvider.currentDateTime.toString()
}