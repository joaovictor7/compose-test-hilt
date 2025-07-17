package com.composetest.core.domain.extension

val String.digits get() = filter { it.isDigit() }

val String?.toIntOrZero get() = this?.toIntOrNull() ?: 0