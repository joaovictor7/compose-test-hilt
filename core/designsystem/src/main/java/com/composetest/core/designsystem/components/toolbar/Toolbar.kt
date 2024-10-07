package com.composetest.core.designsystem.components.toolbar

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
import com.composetest.core.designsystem.extensions.horizontalScreenPadding
import com.composetest.core.designsystem.params.toolbar.ToolbarActionParam
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun Toolbar(
    @StringRes titleId: Int,
    onBackButtonClick: (() -> Unit)? = null,
    actions: List<ToolbarActionParam>? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(topBar = getTopBar(titleId, onBackButtonClick, actions)) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .horizontalScreenPadding(),
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun getTopBar(
    @StringRes titleId: Int,
    onBackButtonClick: (() -> Unit)?,
    actions: List<ToolbarActionParam>?
) = @Composable {
    TopAppBar(
        navigationIcon = getNavigationIcon(onBackButtonClick),
        actions = getActions(actions),
        title = {
            Text(
                text = stringResource(titleId),
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

private fun getNavigationIcon(onBackButtonClick: (() -> Unit)?): @Composable () -> Unit = {
    onBackButtonClick?.let {
        IconButton(onClick = it) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = stringResource(R.string.toolbar_back_button_content_description)
            )
        }
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
            onBackButtonClick = {},
            actions = listOf(ToolbarActionParam(ToolbarAction.EDIT, { }))
        ) { }
    }
}