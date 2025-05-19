package com.composetest.feature.login.domain.usecases

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

internal class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val configurationRepository: ConfigurationRepository,
) {
    suspend operator fun invoke(user: UserModel) {
        userRepository.upsert(user)
        configurationRepository.insertDefaultUserConfiguration(user.id)
    }
}