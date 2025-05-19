package com.composetest.common.extension

val Boolean?.orTrue get() = this != false
val Boolean?.orFalse get() = this == true