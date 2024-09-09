package com.composetest.feature.home.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.dock.DockExtraPadding
import com.composetest.core.designsystem.extensions.defaultTopHorizontalScreenPadding
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen

internal object HomeScreen : Screen<HomeUiState, HomeCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: HomeUiState,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        LazyColumn(modifier = Modifier.defaultTopHorizontalScreenPadding()) {
            item {
                Text(
                    text = "Home11111111111111111111",
                )
            }
            items(17) {
                Button(onClick = { onExecuteCommand(HomeCommand.NavigateToHome2) }) {
                    Text(text = "Go to Home 2")
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
        HomeScreen(uiState = HomeUiState()) { }
    }
}