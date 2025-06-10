package com.composetest.core.designsystem.component.topbar

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getTopBarActions
import com.composetest.core.designsystem.util.getTopBarTitle

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LeftTopBar(
    @StringRes titleId: Int,
    showBackButton: Boolean = true,
    navigationAction: TopBarAction? = null,
    onClickNavigationAction: (() -> Unit)? = null,
    actionIcons: List<TopBarAction>? = null,
    onClickAction: ((TopBarAction) -> Unit)? = null
) {
    TopAppBar(
        navigationIcon = {
            TopBarNavigationIcon(
                showBackButton = showBackButton,
                navigationAction = navigationAction,
                onClickNavigationAction = onClickNavigationAction
            )
        },
        actions = getTopBarActions(actionIcons, onClickAction),
        title = getTopBarTitle(stringResource(titleId))
    )
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        LeftTopBar(titleId = R.string.toolbar_back_button_content_description)
    }
}