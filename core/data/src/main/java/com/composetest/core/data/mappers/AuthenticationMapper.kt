package com.composetest.core.data.mappers

import com.composetest.common.utils.convertFromSeconds
import com.composetest.core.domain.models.session.AuthenticationModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import java.time.LocalDateTime
import javax.inject.Inject

internal class AuthenticationMapper @Inject constructor() {

    operator fun invoke(
        firebaseUser: FirebaseUser?,
        tokenResult: GetTokenResult?,
    ) = AuthenticationModel(
        sessionToken = tokenResult?.token.orEmpty(),
        sessionStartDateTime = tokenResult
            ?.authTimestamp?.let(::convertFromSeconds) ?: LocalDateTime.now(),
        userId = firebaseUser?.uid.orEmpty(),
        userEmail = firebaseUser?.email.orEmpty(),
        userName = firebaseUser?.displayName,
    )
}