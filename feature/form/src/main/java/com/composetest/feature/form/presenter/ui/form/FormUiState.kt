package com.composetest.feature.form.presenter.ui.form

import androidx.compose.ui.text.input.KeyboardType
import com.composetest.core.designsystem.component.textfield.enums.TextFieldIcon
import com.composetest.feature.form.domain.emuns.FormClassification
import com.composetest.feature.form.presenter.enums.FormFieldType
import com.composetest.feature.form.presenter.model.FormTextFieldModel

internal data class FormUiState(
    val fields: List<FormTextFieldModel> = initialFields,
    val classification: FormClassification = FormClassification.EXCELLENT,
) {
    val buttonEnabled get() = fields.all { it.isValid }

    private companion object {
        val initialFields = listOf(
            FormTextFieldModel(FormFieldType.NAME, isValid = true),
            FormTextFieldModel(FormFieldType.EMAIL, keyboardType = KeyboardType.Email),
            FormTextFieldModel(FormFieldType.PHONE_NUMBER, keyboardType = KeyboardType.Number),
            FormTextFieldModel(FormFieldType.PROMOTIONAL_CODE),
            FormTextFieldModel(FormFieldType.DELIVERY_DATE, leadingIcon = TextFieldIcon.CALENDAR),
        )
    }
}
