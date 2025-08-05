package com.composetest.common.api.extension

val Boolean?.orTrue get() = this != false
val Boolean?.orFalse get() = this == true