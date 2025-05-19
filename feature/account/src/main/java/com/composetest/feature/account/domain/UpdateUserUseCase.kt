package com.composetest.feature.account.domain

import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

internal class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(userModel: UserModel) {
        authenticationRepository.updateUserNameAndEmail(userModel.name.orEmpty(), userModel.email)
//        if (userModel.encryptedPassword.isNotEmpty()) {
//            authenticationRepository.updatePassword(userModel.encryptedPassword)
//        }
        userRepository.upsert(userModel)
    }
}