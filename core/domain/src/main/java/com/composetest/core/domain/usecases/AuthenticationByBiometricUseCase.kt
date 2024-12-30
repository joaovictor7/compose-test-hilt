package com.composetest.core.domain.usecases

import com.composetest.core.domain.models.AuthenticationCredentialsModel
import com.composetest.core.domain.providers.CipherProvider
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class AuthenticationByBiometricUseCase @Inject internal constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val userRepository: UserRepository,
    private val cipherProvider: CipherProvider
) {

    suspend operator fun invoke() {
        userRepository.getLastActiveUser()?.let { user ->
            authenticationUseCase(
                AuthenticationCredentialsModel(
                    email = user.email,
                    password = cipherProvider.decrypt(user.encryptedPassword)
                )
            )
        }
    }
}