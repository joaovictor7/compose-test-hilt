package com.composetest.feature.form.presenter.extension

import androidx.annotation.StringRes
import com.composetest.feature.form.R
import com.composetest.feature.form.domain.emuns.FormClassification

internal val FormClassification.textId: Int
    @StringRes get() = when (this) {
        FormClassification.BAD -> R.string.form_bad_classification
        FormClassification.SATISFACTORY -> R.string.form_satisfactory_classification
        FormClassification.GOOD -> R.string.form_good_classification
        FormClassification.VERY_GOOD -> R.string.form_very_good_classification
        FormClassification.EXCELLENT -> R.string.form_excellent_classification
    }