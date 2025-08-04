package com.composetest.feature.form.presenter.model

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType
import com.composetest.core.designsystem.component.textfield.enums.TextFieldIcon
import com.composetest.feature.form.presenter.enums.FormFieldType

internal data class FormTextFieldModel(
    val type: FormFieldType,
    val value: String = String(),
    val keyboardType: KeyboardType = KeyboardType.Text,
    val leadingIcon: TextFieldIcon? = null,
    val isValid: Boolean = false,
    @param:StringRes val errorMsgId: Int? = null,
) {
    val trailingIcon get() = if (errorMsgId != null) TextFieldIcon.ERROR else null
    val isDeliveryDate get() = type == FormFieldType.DELIVERY_DATE
}