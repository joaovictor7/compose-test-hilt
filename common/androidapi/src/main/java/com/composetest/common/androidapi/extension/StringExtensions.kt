package com.composetest.common.androidapi.extension

import androidx.core.util.PatternsCompat

val String.isEmailAddress get() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()