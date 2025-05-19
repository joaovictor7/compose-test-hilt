package com.composetest.core.security.util

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

fun addPBKDF2(data: String, iterations: Int = 10000, keyLength: Int = 512): String {
    val spec = PBEKeySpec(data.toCharArray(), generateSalt(), iterations, keyLength)
    val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
    val hash = factory.generateSecret(spec).encoded
    return hash.joinToString("") { "%02x".format(it) }
}

fun generateSalt(): ByteArray {
    val salt = ByteArray(16)
    SecureRandom().nextBytes(salt)
    return salt
}