package com.composetest.core.designsystem.components.textfields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.enums.textfields.TextFieldIcons
import com.composetest.core.designsystem.params.textfields.TextFieldTrailingIconParam
import com.composetest.core.designsystem.utils.textfields.createIcon
import com.composetest.core.designsystem.utils.textfields.textFieldHelpedText
import com.composetest.core.designsystem.utils.textfields.trailingIcon
import com.composetest.core.designsystem.extensions.opacity
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    textValue: String,
    labelText: String,
    placeholderText: String? = null,
    supportingText: String? = null,
    trailingIconParam: TextFieldTrailingIconParam? = null,
    leadingIcon: TextFieldIcons? = null,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardInput: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onTextChanged: (String) -> Unit
) {
    val passwordHidden = rememberSaveable { mutableStateOf(true) }
    val password = keyboardInput == KeyboardType.Password
    TextField(
        value = textValue,
        enabled = enabled,
        singleLine = singleLine,
        isError = trailingIconParam?.iconType == TextFieldIcons.ERROR,
        readOnly = readOnly,
        modifier = modifier,
        onValueChange = { onTextChanged(it) },
        label = { Text(text = labelText) },
        placeholder = textFieldHelpedText(placeholderText),
        supportingText = textFieldHelpedText(supportingText),
        leadingIcon = createIcon(leadingIcon?.iconId),
        trailingIcon = trailingIcon(
            trailingIconParam,
            textValue,
            password,
            passwordHidden,
            onTextChanged
        ),
        visualTransformation = if (password && passwordHidden.value)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardInput,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            errorContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.opacity(0.04f)
        )
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        val textValue by rememberSaveable { mutableStateOf("teste") }
        TextField(
            enabled = true,
            textValue = textValue,
            labelText = "Label",
            placeholderText = "Placeholder",
            supportingText = "Supporting text",
            trailingIconParam = TextFieldTrailingIconParam(TextFieldIcons.CLEAR_TEXT)
        ) { }
    }
}