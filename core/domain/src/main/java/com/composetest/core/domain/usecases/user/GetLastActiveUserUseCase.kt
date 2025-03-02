package com.composetest.core.domain.usecases.user

import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class GetLastActiveUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke() = userRepository.getLastActiveUser()
}