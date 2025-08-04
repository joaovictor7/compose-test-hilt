package com.composetest.feature.form.domain.model

import com.composetest.feature.form.domain.emuns.FormClassification
import java.time.LocalDateTime

internal data class FormModel(
    val name: String = String(),
    val email: String = String(),
    val phoneNumber: String = String(),
    val promotionalCode: String = String(),
    val deliveryDate: LocalDateTime? = null,
    val classification: FormClassification = FormClassification.EXCELLENT
)