package com.composetest.core.domain.models

data class UserModel(
    val id: String = String(),
    val email: String,
    val encryptedPassword: String,
    val name: String?,
)