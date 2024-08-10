package com.composetest.core.database.providers

internal interface SecretKeyProvider {
    fun getDatabaseSecretKey(): String?
}