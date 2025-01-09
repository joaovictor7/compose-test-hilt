package com.composetest.feature.home.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.home.R
import kotlinx.coroutines.flow.Flow

internal object HomeScreen : Screen<HomeUiState, HomeUiEvent, HomeCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: HomeUiState,
        uiEvent: Flow<HomeUiEvent>?,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        Column(
            modifier = Modifier
                .horizontalScreenMargin()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.home_text),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
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