package com.composetest.feature.login.domain.usecase

import com.composetest.feature.login.data.repository.AuthenticationRepositoryImpl
import javax.inject.Inject

internal class AuthenticationUseCase @Inject internal constructor(
    private val authenticationRepository: AuthenticationRepositoryImpl,
    private val createSessionUseCase: CreateSessionUseCase
) {

    suspend operator fun invoke(email: String, encryptedPassword: String) {
        val authenticationModel = authenticationRepository.authentication(email, encryptedPassword)
        createSessionUseCase(authenticationModel)
    }
}