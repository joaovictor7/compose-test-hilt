package com.composetest.common.api.provider

import java.time.LocalDate
import java.time.LocalDateTime

interface DateTimeProvider {
    val currentDateTime: LocalDateTime
    val currentDate: LocalDate
}