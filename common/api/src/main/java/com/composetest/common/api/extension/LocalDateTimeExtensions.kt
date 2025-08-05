package com.composetest.common.api.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val Long.fromUnixToDateTime: LocalDateTime
    get() = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()

val Long.fromSecondsToDateTime: LocalDateTime
    get() = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(this),
        ZoneId.systemDefault()
    )

fun String.fromStringToDateTime(format: String): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern(format)
    return LocalDateTime.parse(this, formatter)
}

fun LocalDateTime.fromDateTimeToString(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return format(formatter)
}