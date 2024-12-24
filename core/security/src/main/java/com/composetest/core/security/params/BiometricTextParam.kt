package com.composetest.core.security.params

data class BiometricTextParam(
    val title: String,
    val subtitle: String,
    val description: String? = null,
    val negativeButtonText: String
)
