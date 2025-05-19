package com.composetest.core.database.manager

import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.repository.DatabaseRepository
import com.composetest.core.security.provider.CipherProvider
import com.composetest.core.security.util.addPBKDF2
import com.composetest.core.security.util.generateSecureKey
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

    suspend fun getDatabaseCipherFactory() = if (buildConfigProvider.buildConfig.isRelease) {
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