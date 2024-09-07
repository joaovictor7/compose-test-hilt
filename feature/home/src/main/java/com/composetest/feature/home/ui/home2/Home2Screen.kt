package com.composetest.feature.home.ui.home2

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.dock.DockExtraPadding
import com.composetest.core.designsystem.extensions.defaultTopHorizontalPaddings
import com.composetest.core.designsystem.extensions.defaultTopPadding
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen

internal object Home2Screen : Screen<Home2UiState, Home2CommandReceiver> {

    @Composable
    override fun invoke(
        uiState: Home2UiState,
        onExecuteCommand: (Command<Home2CommandReceiver>) -> Unit
    ) {
        LazyColumn(modifier = Modifier.defaultTopHorizontalPaddings()) {
            item {
                Text(text = "Home2", modifier = Modifier.defaultTopPadding())
            }
            items(300) {
                Button(onClick = { onExecuteCommand(Home2Command.ReturnHome) }) {
                    Text(text = uiState.t)
                }
            }
            item {
                DockExtraPadding()
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        Home2Screen(uiState = Home2UiState()) { }
    }
}