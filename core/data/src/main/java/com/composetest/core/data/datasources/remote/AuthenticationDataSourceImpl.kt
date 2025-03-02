package com.composetest.core.data.datasources.remote

import com.composetest.core.data.datasources.AuthenticationDataSource
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.utils.ApiCallUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.tasks.await

internal class AuthenticationDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val authenticationMapper: AuthenticationMapper,
    private val apiCallUtils: ApiCallUtils
) : AuthenticationDataSource {

    override suspend fun authentication(email: String, password: String) =
        apiCallUtils.executeApiCall {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val tokenResult = authResult.user?.getIdToken(true)?.await()
            authenticationMapper.mapperToResponse(authResult.user, tokenResult)
        }

    override suspend fun updateUserProfile() {
        val profileUpdates = userProfileChangeRequest {
            displayName = "Jane Q. User"
        }
        firebaseAuth.currentUser?.updateProfile(profileUpdates)
    }
}