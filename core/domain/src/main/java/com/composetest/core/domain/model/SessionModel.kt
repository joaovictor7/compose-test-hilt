package com.composetest.core.domain.model

import java.time.LocalDateTime

data class SessionModel(
    val id: Long = 0,
    val token: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val isActive: Boolean = true
)
