package com.composetest.core.designsystem.utils.textfields

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.textfields.TextFieldIcons
import com.composetest.core.designsystem.params.textfields.TextFieldTrailingIconParam

internal fun trailingIcon(
    trailingIconParam: TextFieldTrailingIconParam?,
    textValue: String,
    password: Boolean,
    passwordHidden: MutableState<Boolean>,
    onTextChanged: (String) -> Unit
): @Composable (() -> Unit)? = getTrailingParam(trailingIconParam, textValue, onTextChanged)?.let {
    {
        when {
            password -> {
                IconButton(onClick = { passwordHidden.value = !passwordHidden.value }) {
                    createIcon(if (passwordHidden.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off)?.invoke()
                }
            }

            it.onClick != null -> {
                IconButton(onClick = { it.onClick.invoke() }) {
                    createIcon(it.iconType.iconId)?.invoke()
                }
            }

            else -> {
                createIcon(it.iconType.iconId)?.invoke()
            }
        }
    }
}

internal fun createIcon(@DrawableRes iconId: Int?): @Composable (() -> Unit)? = iconId?.let {
    { Icon(painter = painterResource(it), contentDescription = null) }
}

private fun getTrailingParam(
    param: TextFieldTrailingIconParam?,
    textValue: String,
    onTextChanged: (String) -> Unit
) = when {
    param?.iconType != TextFieldIcons.CLEAR_TEXT -> param
    textValue.isNotEmpty() -> param.copy(onClick = { onTextChanged(String()) })
    else -> null
}