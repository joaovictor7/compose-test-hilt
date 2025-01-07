package com.composetest.core.domain.usecases

import com.composetest.core.domain.providers.CipherProvider
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class AuthenticationByBiometricUseCase @Inject internal constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val userRepository: UserRepository,
    private val cipherProvider: CipherProvider
) {

    suspend operator fun invoke() {
        userRepository.getLastUser()?.let { user ->
            authenticationUseCase(user.email, cipherProvider.decrypt(user.encryptedPassword))
        }
    }
}