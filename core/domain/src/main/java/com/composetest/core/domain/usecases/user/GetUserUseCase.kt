package com.composetest.core.domain.usecases.user

import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke() = userRepository.getCurrentUser()
}