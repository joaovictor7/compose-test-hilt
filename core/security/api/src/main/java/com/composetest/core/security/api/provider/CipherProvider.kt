package com.composetest.core.security.api.provider

interface CipherProvider {
    fun encrypt(decryptedData: String): String

    fun decrypt(encryptedData: String): String
}