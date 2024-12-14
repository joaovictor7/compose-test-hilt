package com.composetest.core.designsystem.components.topbar

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
internal fun TopBarNavigationIcon(
    showBackButton: Boolean,
    navigationAction: TopBarAction? = null,
    onClickNavigationAction: (() -> Unit)? = null,
) {
    val (iconId, contentDescription, onClick) = when {
        navigationAction != null -> {
            Triple(
                navigationAction.iconId,
                R.string.toolbar_back_button_content_description
            ) { onClickNavigationAction?.invoke() }
        }
        showBackButton -> {
            val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
            Triple(
                R.drawable.ic_back,
                R.string.toolbar_back_button_content_description
            ) { backDispatcher?.onBackPressed() }
        }
        else -> return
    }
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = stringResource(contentDescription)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        TopBarNavigationIcon(showBackButton = false, navigationAction = TopBarAction.MENU)
    }
}