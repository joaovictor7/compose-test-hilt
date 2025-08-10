package com.composetest.feature.form.presenter.ui.form.viewmodel

import com.composetest.core.ui.interfaces.IntentReceiver
import com.composetest.feature.form.domain.emuns.FormClassification
import java.time.LocalDate

internal interface FormIntentReceiver : IntentReceiver<FormIntentReceiver> {
    fun setFormTextField(index: Int, newValue: String)
    fun formTextFieldFocused(index: Int)
    fun formTextFieldUnfocused(index: Int)
    fun selectedDate(selectedDate: LocalDate)
    fun setClassification(classification: FormClassification)
    fun submitForm()
}