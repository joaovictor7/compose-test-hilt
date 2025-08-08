package com.composetest.core.database.impl.domain.usecase

import com.composetest.core.database.impl.data.repository.DatabaseRepository
import com.composetest.core.security.api.provider.CipherProvider
import com.composetest.core.security.api.utils.addPBKDF2
import com.composetest.core.security.api.utils.generateSecureKey
import javax.inject.Inject

internal class CreateDatabaseKeyUseCase @Inject constructor(
    private val cipherProvider: CipherProvider,
    private val databaseRepository: DatabaseRepository,
) {

    suspend operator fun invoke(): String {
        val secureKey = generateSecureKey(SECURE_KEY_LENGTH)
        return addPBKDF2(secureKey).also {
            databaseRepository.setSqliteSecretKey(cipherProvider.encrypt(it))
        }
    }

    private companion object {
        const val SECURE_KEY_LENGTH = 1000
    }
}