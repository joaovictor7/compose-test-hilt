package com.composetest.core.security.provider

interface CipherProvider {
    fun encrypt(decryptedData: String): String

    fun decrypt(encryptedData: String): String
}