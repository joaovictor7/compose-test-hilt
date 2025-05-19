package com.composetest.feature.account.domain

import com.composetest.core.domain.model.UserModel
import com.composetest.core.domain.repository.AuthenticationRepository
import com.composetest.core.domain.repository.UserRepository
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