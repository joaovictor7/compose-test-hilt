package com.composetest.core.domain.model

data class UserModel(
    val id: String = String(),
    val email: String,
    val encryptedPassword: String,
    val name: String?,
)