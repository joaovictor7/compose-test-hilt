package com.composetest.common.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val Long.convertedFromUnix: LocalDateTime
    get() = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()

val Long.convertedFromSeconds: LocalDateTime
    get() = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(this),
        ZoneId.systemDefault()
    )

fun String.convertToLocalDateTime(format: String): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern(format)
    return LocalDateTime.parse(this, formatter)
}

fun String.convertFromString(format: String): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern(format)
    return LocalDateTime.parse(this, formatter)
}

fun LocalDateTime.convertToString(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return format(formatter)
}