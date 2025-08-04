package com.composetest.core.designsystem.component.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.composetest.common.extension.fromUnixToDate
import com.composetest.core.designsystem.extension.stringResource
import com.composetest.core.ui.R
import java.time.LocalDate

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePicker(
    selectableDates: SelectableDates = DatePickerDefaults.AllDates,
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    val datePickerState = rememberDatePickerState(selectableDates = selectableDates)
    var wasSelectedDate by remember { mutableStateOf(false) }
    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { wasSelectedDate = true }
    }
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                enabled = wasSelectedDate,
                onClick = {
                    datePickerState.selectedDateMillis?.fromUnixToDate?.let {
                        onDateSelected(it)
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.ok),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}