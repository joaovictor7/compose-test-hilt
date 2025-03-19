package com.composetest.feature.login.domain.usecases

import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

internal class AuthenticationByBiometricUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authenticationUseCase: AuthenticationUseCase
) {

    suspend operator fun invoke() {
        userRepository.getLastActiveUser()?.let {
            authenticationUseCase(it.email, it.encryptedPassword)
        }
    }
}