package com.composetest.core.data.datasources.remote

import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.models.session.AuthenticationModel

internal interface AuthenticationDataSource {

    suspend fun authentication(authenticationCredentials: AuthenticationCredentialsModel): AuthenticationModel
}