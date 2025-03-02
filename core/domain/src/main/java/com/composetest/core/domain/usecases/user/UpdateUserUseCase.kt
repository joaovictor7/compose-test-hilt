package com.composetest.core.domain.usecases.user

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(userModel: UserModel) {
        userRepository.upsert(userModel)
    }
}