package com.composetest.common.api.extension

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

val Long.fromUnixToDate: LocalDate
    get() = Instant.ofEpochMilli(this)
        .atZone(ZoneOffset.UTC)
        .toLocalDate()

fun LocalDate.fromDateToString(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return format(formatter)
}
