package com.composetest.core.security.util

fun generateSecureKey(size: Int = 500): String {
    val upperCaseLetters = ('A'..'Z').toList().toCharArray()
    val lowerCaseLetters = ('a'..'z').toList().toCharArray()
    val numbers = ('0'..'9').toList().toCharArray()
    val symbols = "!@#$%^&*()_-=<>?/{}[]|\\:;\"',.~`".toCharArray()
    val characters = upperCaseLetters + lowerCaseLetters + numbers + symbols
    return (1..size)
        .map { characters.random() }
        .joinToString(String())
}