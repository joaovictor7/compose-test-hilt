package com.composetest.core.designsystem.components.toolbar

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.toolbar.ToolbarAction
import com.composetest.core.designsystem.enums.toolbar.ToolbarColor
import com.composetest.core.designsystem.enums.toolbar.ToolbarType
import com.composetest.core.designsystem.params.toolbar.ToolbarActionParam
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    type: ToolbarType = ToolbarType.LEFT,
    color: ToolbarColor = ToolbarColor.SURFACE,
    showBackButton: Boolean = true,
    navigationAction: ToolbarActionParam? = null,
    actions: List<ToolbarActionParam>? = null,
    bottomBar: @Composable () -> Unit = { },
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = getTopBar(type, titleId, showBackButton, navigationAction, actions),
        bottomBar = bottomBar
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .then(modifier),
            content = content
        )
    }
}

private fun getTopBar(
    type: ToolbarType,
    @StringRes titleId: Int,
    showBackButton: Boolean,
    navigationAction: ToolbarActionParam? = null,
    actions: List<ToolbarActionParam>?
) = @Composable {
    when (type) {
        ToolbarType.CENTRALIZED -> CentralizedTopBar(
            titleId = titleId,
            showBackButton = showBackButton,
            navigationAction = navigationAction,
            actions = actions
        )
        ToolbarType.LEFT -> LeftTopBar(
            titleId = titleId,
            showBackButton = showBackButton,
            navigationAction = navigationAction,
            actions = actions
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LeftTopBar(
    @StringRes titleId: Int,
    showBackButton: Boolean,
    navigationAction: ToolbarActionParam?,
    actions: List<ToolbarActionParam>?
) {
    TopAppBar(
        navigationIcon = {
            NavigationIcon(
                navigationAction = navigationAction,
                showBackButton = showBackButton
            )
        },
        actions = getActions(actions),
        title = {
            Text(
                text = stringResource(titleId),
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CentralizedTopBar(
    @StringRes titleId: Int,
    showBackButton: Boolean,
    navigationAction: ToolbarActionParam?,
    actions: List<ToolbarActionParam>?
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            NavigationIcon(
                showBackButton = showBackButton,
                navigationAction = navigationAction
            )
        },
        actions = getActions(actions),
        title = {
            Text(
                text = stringResource(titleId),
                style = MaterialTheme.typography.titleLarge
            )
        }
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
        Toolbar(
            titleId = R.string.global_word_close,
            actions = listOf(ToolbarActionParam(ToolbarAction.EDIT) { })
        ) { }
    }
}