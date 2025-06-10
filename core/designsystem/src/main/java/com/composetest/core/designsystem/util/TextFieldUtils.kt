package com.composetest.core.designsystem.util

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.textfield.TextFieldIcon

internal fun getTextFieldTrailingIcon(
    trailingIcon: TextFieldIcon?,
    onClickTrailingIcon: (() -> Unit)?,
    textValue: String,
    isPassword: Boolean,
    passwordHidden: MutableState<Boolean>,
    onTextChanged: (String) -> Unit
): @Composable (() -> Unit) = {
    when {
        isPassword -> {
            IconButton(onClick = { passwordHidden.value = !passwordHidden.value }) {
                Icon(
                    painter = painterResource(
                        if (passwordHidden.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                    ),
                    contentDescription = null
                )
            }
        }
        trailingIcon != null -> {
            val trailingIconOnCLick =
                getTrailingOnClick(trailingIcon, onClickTrailingIcon, textValue, onTextChanged)
            IconButton(onClick = { trailingIconOnCLick?.invoke() }) {
                Icon(painter = painterResource(trailingIcon.iconId), contentDescription = null)
            }
        }
    }
}

private fun getTrailingOnClick(
    trailingIcon: TextFieldIcon?,
    onClickTrailingIcon: (() -> Unit)?,
    textValue: String,
    onTextChanged: (String) -> Unit
) = when {
    trailingIcon != TextFieldIcon.CLEAR_TEXT -> onClickTrailingIcon
    textValue.isNotEmpty() -> {
        { onTextChanged(String()) }
    }
    else -> null
}