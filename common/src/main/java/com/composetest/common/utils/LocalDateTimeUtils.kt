package com.composetest.common.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun convertFromSeconds(seconds: Long): LocalDateTime = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(seconds),
    ZoneId.systemDefault()
)