package com.composetest.core.database.managers

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.domain.providers.CipherProvider
import com.composetest.core.domain.repositories.DatabaseRepository
import com.composetest.core.security.utils.addPBKDF2
import com.composetest.core.security.utils.generateSecureKey
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DatabaseSecurityManager @Inject constructor(
    private val cipherProvider: CipherProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val databaseRepository: DatabaseRepository
) {

    init {
        loadSqlCipherLibrary()
    }

    suspend fun getDatabaseCipherFactory() = if (buildConfigProvider.get.isRelease) {
        val databaseKey = getOrCreateDatabaseKey()
        SupportOpenHelperFactory(databaseKey.toByteArray())
    } else null

    private suspend fun getOrCreateDatabaseKey(): String {
        val encryptedDatabaseKey = databaseRepository.getSqliteSecretKey()
        return if (encryptedDatabaseKey == null) {
            val secureKey = generateSecureKey(1000)
            addPBKDF2(secureKey).also {
                databaseRepository.setSqliteSecretKey(cipherProvider.encrypt(it))
            }
        } else {
            cipherProvider.decrypt(encryptedDatabaseKey)
        }
    }

    private fun loadSqlCipherLibrary() {
        System.loadLibrary("sqlcipher")
    }
}