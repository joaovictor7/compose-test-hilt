package com.composetest.core.domain.extension

val Boolean?.orTrue get() = this != false
val Boolean?.orFalse get() = this == true