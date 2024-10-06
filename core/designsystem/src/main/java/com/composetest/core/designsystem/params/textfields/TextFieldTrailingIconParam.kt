package com.composetest.core.designsystem.params.textfields

import com.composetest.core.designsystem.enums.textfields.TextFieldIcons

data class TextFieldTrailingIconParam(
    val iconType: TextFieldIcons,
    val onClick: (() -> Unit)? = null
)