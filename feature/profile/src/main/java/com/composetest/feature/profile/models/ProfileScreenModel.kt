package com.composetest.feature.profile.models

import androidx.annotation.StringRes
import androidx.compose.ui.text.AnnotatedString

internal data class ProfileScreenModel(
    @StringRes val titleId: Int,
    val text: AnnotatedString
)