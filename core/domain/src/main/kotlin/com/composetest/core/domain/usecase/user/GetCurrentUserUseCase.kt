package com.composetest.core.domain.usecase.user

import com.composetest.core.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getCurrentUser()
}