package com.composetest.feature.login.presenter.model

import androidx.core.util.PatternsCompat

internal data class LoginFormModel(
    val email: String = String(),
    val password: String = String()
) {
    val emailIsValid get() = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    val emailIsNotEmpty get() = email.isNotEmpty()
    val loginAlready get() = email.isNotEmpty() and password.isNotEmpty() and emailIsValid
}
