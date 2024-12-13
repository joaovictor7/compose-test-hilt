package com.composetest.core.designsystem.components.toolbar

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.params.toolbar.ToolbarActionParam
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LeftTopBar(
    @StringRes titleId: Int,
    showBackButton: Boolean = true,
    navigationAction: ToolbarActionParam? = null,
    actions: List<ToolbarActionParam>? = null
) {
    TopAppBar(
        navigationIcon = {
            NavigationIcon(
                navigationAction = navigationAction,
                showBackButton = showBackButton
            )
        },
        actions = getActions(actions),
        title = getTitleBar(titleId)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CentralizedTopBar(
    @StringRes titleId: Int,
    showBackButton: Boolean = true,
    navigationAction: ToolbarActionParam? = null,
    actions: List<ToolbarActionParam>? = null
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            NavigationIcon(
                showBackButton = showBackButton,
                navigationAction = navigationAction
            )
        },
        actions = getActions(actions),
        title = getTitleBar(titleId)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarWithoutTitle(
    showBackButton: Boolean = true,
    navigationAction: ToolbarActionParam? = null,
    actions: List<ToolbarActionParam>? = null
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            NavigationIcon(
                showBackButton = showBackButton,
                navigationAction = navigationAction
            )
        },
        actions = getActions(actions),
    )
}

private fun getTitleBar(@StringRes titleId: Int): @Composable () -> Unit = {
    Text(
        text = stringResource(titleId),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun NavigationIcon(
    showBackButton: Boolean,
    navigationAction: ToolbarActionParam?
) {
    val (iconId, contentDescription, onClick) = when {
        navigationAction != null -> {
            Triple(
                navigationAction.action.iconId,
                R.string.toolbar_back_button_content_description
            ) { navigationAction.onClickAction() }
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

private fun getActions(actions: List<ToolbarActionParam>?): @Composable RowScope.() -> Unit = {
    actions?.forEach {
        IconButton(onClick = it.onClickAction) {
            Icon(
                painter = painterResource(it.action.iconId),
                contentDescription = null
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        Column(verticalArrangement = Arrangement.spacedBy(spacings.twenty)) {
            TopBarWithoutTitle()
            LeftTopBar(titleId = R.string.toolbar_back_button_content_description)
            CentralizedTopBar(titleId = R.string.toolbar_back_button_content_description)
        }
    }
}