package com.composetest.core.designsystem.component.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getTopBarActions

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarWithoutTitle(
    showBackButton: Boolean = true,
    navigationAction: TopBarAction? = null,
    onClickNavigationAction: (() -> Unit)? = null,
    actionIcons: List<TopBarAction>? = null,
    onClickAction: ((TopBarAction) -> Unit)? = null
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            TopBarNavigationIcon(
                showBackButton = showBackButton,
                navigationAction = navigationAction,
                onClickNavigationAction = onClickNavigationAction
            )
        },
        actions = getTopBarActions(actionIcons, onClickAction),
    )
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        TopBarWithoutTitle()
    }
}