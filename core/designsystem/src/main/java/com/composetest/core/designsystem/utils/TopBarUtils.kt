package com.composetest.core.designsystem.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.composetest.core.designsystem.enums.topbar.TopBarAction

internal fun getTopBarTitle(@StringRes titleId: Int): @Composable () -> Unit = {
    Text(
        text = stringResource(titleId),
        style = MaterialTheme.typography.titleLarge
    )
}

internal fun getTopBarActions(
    actions: List<TopBarAction>?,
    onClickAction: ((TopBarAction) -> Unit)?
): @Composable RowScope.() -> Unit = {
    actions?.forEach {
        IconButton(onClick = { onClickAction?.invoke(it) }) {
            Icon(
                painter = painterResource(it.iconId),
                contentDescription = null
            )
        }
    }
}