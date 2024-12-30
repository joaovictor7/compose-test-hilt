package com.composetest.core.domain.providers

interface CipherProvider {
    fun encrypt(inputText: String): String

    fun decrypt(data: String): String
}