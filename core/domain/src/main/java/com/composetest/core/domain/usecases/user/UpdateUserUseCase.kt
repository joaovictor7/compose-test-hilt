package com.composetest.core.domain.usecases.user

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(userModel: UserModel) {
        authenticationRepository.updateUserNameAndEmail(userModel.name.orEmpty(), userModel.email)
//        if (userModel.encryptedPassword.isNotEmpty()) {
//            authenticationRepository.updatePassword(userModel.encryptedPassword)
//        }
        userRepository.update(userModel)
    }
}