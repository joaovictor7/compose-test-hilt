package com.composetest.common.extensions

val Boolean?.orTrue get() = this != false
val Boolean?.orFalse get() = this == true