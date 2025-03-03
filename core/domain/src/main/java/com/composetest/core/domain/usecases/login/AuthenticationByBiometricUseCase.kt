package com.composetest.core.domain.usecases.login

import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class AuthenticationByBiometricUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authenticationUseCase: AuthenticationUseCase
) {

    suspend operator fun invoke() {
        userRepository.getLastActiveUser()?.let {
            authenticationUseCase(it.email, it.encryptedPassword)
        }
    }
}