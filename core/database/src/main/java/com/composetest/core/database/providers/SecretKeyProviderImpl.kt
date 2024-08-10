package com.composetest.core.database.providers

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.security.providers.CipherProvider
import com.composetest.core.security.utils.getAlphanumericRandomKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class SecretKeyProviderImpl @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider,
    private val dataStore: DataStore<Preferences>,
    private val cipherProvider: CipherProvider
) : SecretKeyProvider {

    override fun getDatabaseSecretKey() = if (buildConfigProvider.get.isRelease) runBlocking {
        val encryptedKey = getSecretKey()
        if (encryptedKey == null) getAlphanumericRandomKey(SECRET_KEY_SIZE).also {
            setSecretKey(cipherProvider.encrypt(it))
        } else cipherProvider.decrypt(encryptedKey)
    } else null

    private suspend fun getSecretKey() = dataStore.data.map { preferences ->
        preferences[sqliteSecretKey]
    }.firstOrNull()

    private suspend fun setSecretKey(secretKey: String) = dataStore.edit {
        it[sqliteSecretKey] = secretKey
    }

    private companion object {
        const val SECRET_KEY_SIZE = 300
        val sqliteSecretKey = stringPreferencesKey("sqlite_secret_key")
    }
}