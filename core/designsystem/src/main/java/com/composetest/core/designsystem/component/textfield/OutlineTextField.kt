package com.composetest.core.designsystem.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.enums.textfield.TextFieldIcon
import com.composetest.core.designsystem.extension.opacity
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getTextFieldTrailingIcon

@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    textValue: String,
    labelText: String,
    placeholderText: String? = null,
    supportingText: String? = null,
    trailingIcon: TextFieldIcon? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    leadingIcon: TextFieldIcon? = null,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardInput: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onFocusChanged: ((FocusState) -> Unit)? = null,
    onTextChanged: (String) -> Unit
) {
    val passwordHidden = rememberSaveable { mutableStateOf(true) }
    val isPassword = keyboardInput == KeyboardType.Password
    OutlinedTextField(
        value = textValue,
        enabled = enabled,
        singleLine = singleLine,
        isError = trailingIcon == TextFieldIcon.ERROR,
        readOnly = readOnly,
        modifier = modifier.onFocusChanged { onFocusChanged?.invoke(it) },
        onValueChange = { onTextChanged(it) },
        label = { Text(text = labelText, style = MaterialTheme.typography.bodyLarge) },
        placeholder = placeholderText?.let {
            { Text(text = it, style = MaterialTheme.typography.bodyLarge) }
        },
        supportingText = supportingText?.let {
            { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
        },
        leadingIcon = leadingIcon?.iconId?.let {
            { Icon(painter = painterResource(it), contentDescription = null) }
        },
        trailingIcon = getTextFieldTrailingIcon(
            trailingIcon,
            onTrailingIconClick,
            textValue,
            isPassword,
            passwordHidden,
            onTextChanged
        ),
        visualTransformation = if (isPassword && passwordHidden.value)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardInput,
            imeAction = imeAction
        ),
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = MaterialTheme.colorScheme.outline.opacity(0.12f)
        )
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        val textValue by rememberSaveable { mutableStateOf("teste") }
        OutlinedTextField(
            labelText = "Label",
            textValue = textValue,
            supportingText = "teste",
            placeholderText = "Placeholder",
            keyboardInput = KeyboardType.Password
        ) { }
    }
}