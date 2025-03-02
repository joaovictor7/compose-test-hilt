package com.composetest.core.security.providers

interface CipherProvider {
    fun encrypt(decryptedData: String): String

    fun decrypt(encryptedData: String): String
}