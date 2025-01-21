package com.composetest.core.domain.providers

interface CipherProvider {
    fun encrypt(decryptedData: String): String

    fun decrypt(encryptedData: String): String
}