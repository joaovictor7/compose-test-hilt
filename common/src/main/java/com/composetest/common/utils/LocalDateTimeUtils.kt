package com.composetest.common.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun convertFromSeconds(seconds: Long) = LocalDateTime.ofInstant(
    Instant.ofEpochSecond(seconds),
    ZoneId.systemDefault()
)