package com.composetest.feature.login.data.mapper

import com.composetest.common.extension.convertedFromSeconds
import com.composetest.common.provider.DateTimeProvider
import com.composetest.feature.login.domain.model.AuthenticationModel
import com.composetest.feature.login.network.response.AuthenticationResponse
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
        encryptedPassword: String
    ) = AuthenticationModel(
        sessionToken = authenticationResponse.sessionToken,
        sessionStartDateTime = LocalDateTime.parse(authenticationResponse.sessionStartDateTime),
        user = userMapper.mapperToModel(authenticationResponse, encryptedPassword)
    )

    private fun GetTokenResult?.formatDateTime() =
        this?.authTimestamp?.convertedFromSeconds?.toString()
            ?: dateTimeProvider.currentDateTime.toString()
}