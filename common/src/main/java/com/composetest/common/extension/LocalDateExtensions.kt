package com.composetest.common.extension

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

val Long.fromUnixToDate: LocalDate
    get() = Instant.ofEpochMilli(this)
        .atZone(ZoneOffset.UTC)
        .toLocalDate()