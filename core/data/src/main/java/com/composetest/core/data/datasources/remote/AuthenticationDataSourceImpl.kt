package com.composetest.core.data.datasources.remote

import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.utils.RemoteCallUtils
import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.tasks.await

internal class AuthenticationDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val authenticationMapper: AuthenticationMapper,
    private val remoteCallUtils: RemoteCallUtils
) : AuthenticationDataSource {

    override suspend fun authentication(authenticationCredentials: AuthenticationCredentialsModel) =
        remoteCallUtils.executeRemoteCall {
            val authResult = firebaseAuth.signInWithEmailAndPassword(
                authenticationCredentials.email,
                authenticationCredentials.password
            ).await()
            val tokenResult = authResult.user?.getIdToken(true)?.await()
            authenticationMapper(authResult.user, tokenResult)
        }

    override suspend fun updateUserProfile() {
        val profileUpdates = userProfileChangeRequest {
            displayName = "Jane Q. User"
        }
        firebaseAuth.currentUser?.updateProfile(profileUpdates)
    }
}