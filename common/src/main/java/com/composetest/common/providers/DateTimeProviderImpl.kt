package com.composetest.common.providers

import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

internal class DateTimeProviderImpl @Inject constructor() : DateTimeProvider {
    override val currentDateTime: LocalDateTime get() = LocalDateTime.now()
    override val currentDate: LocalDate get() = currentDateTime.toLocalDate()
}