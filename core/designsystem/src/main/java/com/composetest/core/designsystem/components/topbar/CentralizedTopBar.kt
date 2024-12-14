package com.composetest.core.designsystem.components.topbar

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.utils.getTopBarActions
import com.composetest.core.designsystem.utils.getTopBarTitle

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CentralizedTopBar(
    @StringRes titleId: Int,
    showBackButton: Boolean = true,
    navigationAction: TopBarAction? = null,
    onClickNavigationAction: (() -> Unit)? = null,
    actionIcons: List<TopBarAction>? = null,
    onClickAction: ((TopBarAction) -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            TopBarNavigationIcon(
                showBackButton = showBackButton,
                navigationAction = navigationAction,
                onClickNavigationAction = onClickNavigationAction
            )
        },
        actions = getTopBarActions(actionIcons, onClickAction),
        title = getTopBarTitle(titleId)
    )
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        CentralizedTopBar(titleId = R.string.toolbar_back_button_content_description)
    }
}