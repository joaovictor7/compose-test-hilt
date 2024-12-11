package com.composetest.feature.home.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import kotlinx.coroutines.flow.Flow

internal object HomeScreen : Screen<HomeUiState, HomeUiEvent, HomeCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: HomeUiState,
        uiEvent: Flow<HomeUiEvent>?,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier.horizontalScreenMargin(),
            contentPadding = PaddingValues(top = screenMargin)
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
        HomeScreen(uiState = HomeUiState(), uiEvent = null) { }
    }
}