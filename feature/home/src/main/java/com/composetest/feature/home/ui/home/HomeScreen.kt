package com.composetest.feature.home.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.constants.topScreenPaddingList
import com.composetest.core.designsystem.extensions.horizontalScreenPadding
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen

internal object HomeScreen : Screen<HomeUiState, HomeCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: HomeUiState,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier.horizontalScreenPadding(),
            contentPadding = topScreenPaddingList
        ) {
            item {
                Text(
                    text = "Home11111111111111111111",
                )
            }
            items(30) {
                Button(onClick = { onExecuteCommand(HomeCommand.NavigateToHome2) }) {
                    Text(text = "Go to Home 2")
                }
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