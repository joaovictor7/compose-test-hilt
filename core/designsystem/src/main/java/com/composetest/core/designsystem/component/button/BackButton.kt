package com.composetest.core.designsystem.component.button

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun BackButton(modifier: Modifier = Modifier) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
        Icon(
            modifier = modifier,
            painter = painterResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.toolbar_back_button_content_description)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        BackButton()
    }
}