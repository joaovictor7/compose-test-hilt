package com.composetest.core.data.mappers

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.models.session.AuthenticationModel
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

internal class AuthenticationMapper @Inject constructor() {

    operator fun invoke(firebaseUser: FirebaseUser?) = AuthenticationModel(
        sessionToken = firebaseUser?.tenantId.orEmpty(),
        user = UserModel(
            id = firebaseUser?.uid.orEmpty(),
            email = firebaseUser?.email.orEmpty(),
            name = firebaseUser?.email
        )
    )
}