package com.composetest.feature.home.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.common.enums.Theme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen

internal object HomeScreen : Screen<HomeUiState, HomeCommandReceiver> {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun invoke(
        uiState: HomeUiState,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        Scaffold { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Button(onClick = { onExecuteCommand(NavigateToHome2) }) {
                    Text(text = uiState.t)
                }
                Text(text = "Home1")
                Button(onClick = { onExecuteCommand(ChangeAppTheme(theme = Theme.AUTO)) }) {
                    Text(text = "Auto")
                }
                Button(onClick = { onExecuteCommand(ChangeAppTheme(theme = Theme.LIGHT)) }) {
                    Text(text = "Light")
                }
                Button(onClick = { onExecuteCommand(ChangeAppTheme(theme = Theme.DARK)) }) {
                    Text(text = "Dark")
                }
                Button(onClick = { onExecuteCommand(ChangeAppTheme(dynamicColors = true)) }) {
                    Text(text = "Dynamic on")
                }
                Button(onClick = { onExecuteCommand(ChangeAppTheme(dynamicColors = false)) }) {
                    Text(text = "Dynamic off")
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