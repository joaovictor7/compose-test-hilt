package com.composetest.core.designsystem.component.topbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.component.button.BackButton
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
internal fun TopBarNavigationIcon(
    showBackButton: Boolean,
    navigationAction: TopBarAction? = null,
    onClickNavigationAction: (() -> Unit)? = null,
) {
    when {
        navigationAction != null -> {
            IconButton(onClick = { onClickNavigationAction?.invoke() }) {
                Icon(
                    painter = painterResource(navigationAction.iconId),
                    contentDescription = null
                )
            }
        }
        showBackButton -> {
            BackButton()
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        TopBarNavigationIcon(showBackButton = false, navigationAction = TopBarAction.MENU)
    }
}