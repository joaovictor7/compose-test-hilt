package com.composetest.feature.form.presenter.ui.form

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.form.R
import com.composetest.feature.form.domain.emuns.FormClassification
import com.composetest.feature.form.presenter.enums.FormFieldType
import com.composetest.feature.form.presenter.model.FormTextFieldModel
import com.composetest.feature.form.presenter.ui.form.viewmodel.FormUiState

@PreviewTest
@Preview(showBackground = true)
@Composable
private fun FormScreenInitialStatePreview() {
    ComposeTestTheme {
        FormScreen(
            uiState = FormUiState(
                fields = listOf(
                    FormTextFieldModel(type = FormFieldType.NAME, value = ""),
                    FormTextFieldModel(type = FormFieldType.EMAIL, value = ""),
                    FormTextFieldModel(
                        type = FormFieldType.DELIVERY_DATE,
                        value = "",
                    ),
                    FormTextFieldModel(
                        type = FormFieldType.PHONE_NUMBER,
                        value = "",
                        keyboardType = KeyboardType.Number
                    ),
                ),
            )
        )
    }
}

@Composable
@PreviewTest
@Preview(name = "FormScreen - Filled State", showBackground = true)
private fun FormScreenFilledStatePreview() {
    ComposeTestTheme {
        FormScreen(
            uiState = FormUiState(
                fields = listOf(
                    FormTextFieldModel(type = FormFieldType.NAME, value = "John Doe"),
                    FormTextFieldModel(
                        type = FormFieldType.EMAIL,
                        value = "john.doe@example.com"
                    ),
                    FormTextFieldModel(
                        type = FormFieldType.DELIVERY_DATE,
                        value = "2025-07-15",
                    ),
                ),
            )
        )
    }
}

@Composable
@PreviewTest
@Preview(name = "FormScreen - With Errors", showBackground = true)
private fun FormScreenWithErrorsPreview() {
    ComposeTestTheme {
        FormScreen(
            uiState = FormUiState(
                fields = listOf(
                    FormTextFieldModel(
                        type = FormFieldType.NAME,
                        value = "",
                        errorMsgId = R.string.form_email_error
                    ),
                    FormTextFieldModel(
                        type = FormFieldType.EMAIL,
                        value = "invalid",
                        errorMsgId = R.string.form_email_error
                    ),
                    FormTextFieldModel(
                        type = FormFieldType.DELIVERY_DATE,
                        value = "",
                    ),
                ),
            )
        )
    }
}

@Composable
@PreviewTest
@Preview(name = "FormScreen - Service Classification Selected", showBackground = true)
private fun FormScreenServiceClassificationSelectedPreview() {
    ComposeTestTheme {
        FormScreen(
            uiState = FormUiState(
                fields = listOf(
                    FormTextFieldModel(type = FormFieldType.NAME, value = "Jane Smith"),
                    FormTextFieldModel(
                        type = FormFieldType.EMAIL,
                        value = "jane.smith@example.com"
                    ),
                    FormTextFieldModel(
                        type = FormFieldType.DELIVERY_DATE,
                        value = "2025-08-01",
                    ),
                ),
                classification = FormClassification.BAD,
            )
        )
    }
}