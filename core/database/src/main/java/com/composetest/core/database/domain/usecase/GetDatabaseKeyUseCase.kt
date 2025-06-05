package com.composetest.core.database.domain.usecase

import com.composetest.core.database.data.repository.DatabaseRepository
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.security.provider.CipherProvider
import com.composetest.core.security.util.addPBKDF2
import com.composetest.core.security.util.generateSecureKey
import javax.inject.Inject

internal class GetDatabaseKeyUseCase @Inject constructor(
    private val cipherProvider: CipherProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val databaseRepository: DatabaseRepository,
) {

    suspend operator fun invoke() = if (buildConfigProvider.buildConfig.isRelease) {
        getOrCreateDatabaseKey().toByteArray()
    } else null

    private suspend fun getOrCreateDatabaseKey(): String {
        val encryptedDatabaseKey = databaseRepository.getSqliteSecretKey()
        return if (encryptedDatabaseKey == null) {
            val secureKey = generateSecureKey(SECURE_KEY_LENGTH)
            addPBKDF2(secureKey).also {
                databaseRepository.setSqliteSecretKey(cipherProvider.encrypt(it))
            }
        } else {
            cipherProvider.decrypt(encryptedDatabaseKey)
        }
    }

    private companion object {
        const val SECURE_KEY_LENGTH = 1000
    }
}