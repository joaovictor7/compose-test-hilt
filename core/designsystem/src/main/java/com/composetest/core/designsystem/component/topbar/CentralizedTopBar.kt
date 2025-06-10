package com.composetest.core.designsystem.component.topbar

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.enums.topbar.TopBarColor
import com.composetest.core.designsystem.enums.topbar.TopBarColor.Companion.getColor
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getTopBarActions
import com.composetest.core.designsystem.util.getTopBarTitle

@Composable
fun CentralizedTopBar(
    @StringRes titleId: Int,
    color: TopBarColor = TopBarColor.SURFACE,
    showBackButton: Boolean = true,
    navigationAction: TopBarAction? = null,
    onClickNavigationAction: (() -> Unit)? = null,
    actionIcons: List<TopBarAction>? = null,
    onClickAction: ((TopBarAction) -> Unit)? = null
) {
    CentralizedTopBar(
        title = stringResource(titleId),
        color = color,
        showBackButton = showBackButton,
        navigationAction = navigationAction,
        onClickNavigationAction = onClickNavigationAction,
        actionIcons = actionIcons,
        onClickAction = onClickAction
    )
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CentralizedTopBar(
    title: String,
    color: TopBarColor = TopBarColor.SURFACE,
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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = color.getColor()),
        actions = getTopBarActions(actionIcons, onClickAction),
        title = getTopBarTitle(title)
    )
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        CentralizedTopBar(titleId = R.string.toolbar_back_button_content_description)
    }
}