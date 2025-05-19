package com.composetest.feature.login.domain.usecase

import com.composetest.core.domain.model.UserModel
import com.composetest.core.domain.repository.ConfigurationRepository
import com.composetest.core.domain.repository.UserRepository
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