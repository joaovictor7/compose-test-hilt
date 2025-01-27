package com.composetest.common.extensions

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

val Long.unixToLocalDateTime: LocalDateTime
    get() = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()

val Long.convertedFromSeconds get() =  LocalDateTime.ofInstant(
    Instant.ofEpochSecond(this),
    ZoneId.systemDefault()
)