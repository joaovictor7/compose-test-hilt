package com.composetest.core.designsystem.components.toolbar

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
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
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.params.toolbar.ToolbarActionParam
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun Toolbar(
    @StringRes titleId: Int,
    showBackButton: Boolean = true,
    actions: List<ToolbarActionParam>? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(topBar = getTopBar(titleId, showBackButton, actions)) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .horizontalScreenMargin(),
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun getTopBar(
    @StringRes titleId: Int,
    showBackButton: Boolean,
    actions: List<ToolbarActionParam>?
) = @Composable {
    TopAppBar(
        navigationIcon = { NavigationIcon(showBackButton = showBackButton) },
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
private fun NavigationIcon(showBackButton: Boolean) {
    if (!showBackButton) return
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.toolbar_back_button_content_description)
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
            actions = listOf(ToolbarActionParam(ToolbarAction.EDIT, { }))
        ) { }
    }
}