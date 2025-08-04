package com.productschallenger.feature.form

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.form.domain.emuns.FormClassification
import com.composetest.feature.form.presenter.enums.FormFieldType
import com.composetest.feature.form.presenter.model.FormTextFieldModel
import com.composetest.feature.form.presenter.ui.form.FormScreen
import com.composetest.feature.form.presenter.ui.form.FormUiState

internal class FormScreenTest {
    @Preview
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

    @Preview(name = "FormScreen - Filled State", showBackground = true)
    @Composable
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
                            isDeliveryDate = true
                        ),
                    ),
                )
            )
        }
    }

    @Preview(name = "FormScreen - With Errors", showBackground = true)
    @Composable
    private fun FormScreenWithErrorsPreview() {
        ComposeTestTheme {
            FormScreen(
                uiState = FormUiState(
                    fields = listOf(
                        FormTextFieldModel(
                            type = FormFieldType.NAME,
                            value = "",
                            errorMsgId = R.string.form_error_name_required
                        ),
                        FormTextFieldModel(
                            type = FormFieldType.EMAIL,
                            value = "invalid",
                            errorMsgId = R.string.form_error_email_invalid
                        ),
                        FormTextFieldModel(
                            type = FormFieldType.DELIVERY_DATE,
                            value = "",
                            isDeliveryDate = true
                        ),
                    ),
                )
            )
        }
    }

    @Preview(name = "FormScreen - Service Classification Selected", showBackground = true)
    @Composable
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
                            isDeliveryDate = true
                        ),
                    ),
                    classification = FormClassification.BAD,
                )
            )
        }
    }
}