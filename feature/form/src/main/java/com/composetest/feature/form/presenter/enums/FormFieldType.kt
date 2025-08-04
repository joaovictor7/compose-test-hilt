package com.composetest.feature.form.presenter.enums

import androidx.annotation.StringRes
import com.composetest.feature.form.R

internal enum class FormFieldType(@param:StringRes val labelId: Int) {
    NAME(R.string.form_name_label),
    EMAIL(R.string.form_email_label),
    PHONE_NUMBER(R.string.form_number_label),
    PROMOTIONAL_CODE(R.string.form_promotional_code_label),
    DELIVERY_DATE(R.string.form_delivery_date_label),
}