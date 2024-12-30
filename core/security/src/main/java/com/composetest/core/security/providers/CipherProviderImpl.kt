package com.composetest.core.security.providers

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.composetest.core.domain.providers.CipherProvider
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.inject.Inject

internal class CipherProviderImpl @Inject constructor() : CipherProvider {

    private val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE).apply {
        load(null)
    }
    private val secretKey by lazy {
        val existingKey = keyStore.getEntry(KEY_ALIAS, null) as? KeyStore.SecretKeyEntry
        existingKey?.secretKey ?: createKey()
    }

    override fun encrypt(inputText: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(inputText.toByteArray())
        val iv = cipher.iv
        val encryptedDataWithIV = ByteArray(iv.size + encryptedBytes.size)
        System.arraycopy(iv, 0, encryptedDataWithIV, 0, iv.size)
        System.arraycopy(encryptedBytes, 0, encryptedDataWithIV, iv.size, encryptedBytes.size)
        return Base64.encodeToString(encryptedDataWithIV, Base64.DEFAULT)
    }

    override fun decrypt(data: String): String {
        val encryptedDataWithIV = Base64.decode(data, Base64.DEFAULT)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = encryptedDataWithIV.copyOfRange(0, cipher.blockSize)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        val encryptedData = encryptedDataWithIV.copyOfRange(
            cipher.blockSize,
            encryptedDataWithIV.size
        )
        val decryptedBytes = cipher.doFinal(encryptedData)
        return String(decryptedBytes, Charsets.UTF_8)
    }

    private fun createKey(): SecretKey {
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(BLOCK_MODE)
            .setEncryptionPaddings(PADDING)
            .setUserAuthenticationRequired(false)
            .setRandomizedEncryptionRequired(true)
            .setKeySize(KEY_SIZE)
            .build()

        return KeyGenerator.getInstance(AES_ALGORITHM).apply {
            init(keyGenParameterSpec)
        }.generateKey()
    }

    private companion object {
        const val ANDROID_KEY_STORE = "AndroidKeyStore"
        const val KEY_ALIAS = "aes_key_alias"
        const val KEY_SIZE = 256
        const val AES_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        const val TRANSFORMATION = "$AES_ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}