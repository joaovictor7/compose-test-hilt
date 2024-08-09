package com.composetest.core.domain.models.session

import java.time.LocalDateTime

data class SessionModel(
    val id: Long = 0,
    val token: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val isFinished: Boolean
)
