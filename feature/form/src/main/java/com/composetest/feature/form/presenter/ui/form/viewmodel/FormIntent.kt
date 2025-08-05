package com.composetest.feature.form.presenter.ui.form.viewmodel

import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.form.domain.emuns.FormClassification
import java.time.LocalDate

internal sealed interface FormIntent : Intent<FormIntentReceiver> {
    data class SetFormTextField(
        private val index: Int,
        private val newValue: String,
    ) : FormIntent {
        override fun execute(intentReceiver: FormIntentReceiver) {
            intentReceiver.setFormTextField(index, newValue)
        }
    }

    data class FormTextFieldFocused(private val index: Int) : FormIntent {
        override fun execute(intentReceiver: FormIntentReceiver) {
            intentReceiver.formTextFieldFocused(index)
        }
    }

    data class FormTextFieldUnfocused(private val index: Int) : FormIntent {
        override fun execute(intentReceiver: FormIntentReceiver) {
            intentReceiver.formTextFieldUnfocused(index)
        }
    }

    data class SelectedDate(
        private val selectedDate: LocalDate,
    ) : FormIntent {
        override fun execute(intentReceiver: FormIntentReceiver) {
            intentReceiver.selectedDate(selectedDate)
        }
    }

    data class SetClassification(
        private val classification: FormClassification,
    ) : FormIntent {
        override fun execute(intentReceiver: FormIntentReceiver) {
            intentReceiver.setClassification(classification)
        }
    }

    data object SubmitForm : FormIntent {
        override fun execute(intentReceiver: FormIntentReceiver) {
            intentReceiver.submitForm()
        }
    }

    data object DismissSimpleDialog : FormIntent {
        override fun execute(intentReceiver: FormIntentReceiver) {
            intentReceiver.dismissSimpleDialog()
        }
    }
}