package com.composetest.core.database.managers

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.domain.providers.CipherProvider
import com.composetest.core.domain.repositories.SecretKeyRepository
import com.composetest.core.security.utils.addPBKDF2
import kotlinx.coroutines.flow.firstOrNull
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DatabaseSecurityManager @Inject constructor(
    private val cipherProvider: CipherProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val secretKeyRepository: SecretKeyRepository
) {

    init {
        loadSqlCipherLibrary()
    }

    suspend fun getDatabaseCipherFactory() = if (buildConfigProvider.get.isRelease) {
        val databaseKey = getOrCreateDatabaseKey()
        SupportOpenHelperFactory(databaseKey.toByteArray())
    } else null

    private suspend fun getOrCreateDatabaseKey(): String {
        val encryptedDatabaseKey = secretKeyRepository.getSqliteSecretKey().firstOrNull()
        return if (encryptedDatabaseKey == null) {
            addPBKDF2(buildConfigProvider.get.buildConfigFieldsModel.databaseKey).also {
                secretKeyRepository.setSqliteSecretKey(cipherProvider.encrypt(it))
            }
        } else {
            cipherProvider.decrypt(encryptedDatabaseKey)
        }
    }

    private fun loadSqlCipherLibrary() {
        System.loadLibrary("sqlcipher")
    }
}