package com.composetest.feature.form.presenter.ui.form.viewmodel

import android.text.TextUtils
import androidx.core.text.isDigitsOnly
import app.cash.turbine.test
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.test.android.BaseTest
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.form.R
import com.composetest.feature.form.analytic.screen.FormScreenAnalytic
import com.composetest.feature.form.domain.emuns.FormClassification
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class FormViewModelTest : BaseTest() {

    private val analyticSender: AnalyticSender = mockk(relaxed = true)
    private lateinit var viewModel: FormViewModel

    private val asyncTaskUtils = AsyncTaskUtils(analyticSender, FormScreenAnalytic)

    @BeforeEach
    fun setUp() {
        viewModel = initViewModel()
    }

    @Test
    fun `init Should send open screen analytic`() = runTest {
        coVerify { analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(FormScreenAnalytic)) }
    }

    @Test
    fun `setFormTextField Should update email field When email is valid`() = runTest {
        viewModel.executeIntent(FormIntent.SetFormTextField(1, "user@email.com"))
        viewModel.uiState.test {
            awaitItem().fields[1].let { field ->
                assertEquals("user@email.com", field.value)
                assertTrue(field.isValid)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `setFormTextField Should update phone field When input is numeric`() = runTest {
        mockkStatic(TextUtils::class)
        every { any<String>().isDigitsOnly() } returns true
        viewModel.executeIntent(FormIntent.SetFormTextField(2, "999999999"))
        viewModel.uiState.test {
            awaitItem().fields[2].let { field ->
                assertEquals("999999999", field.value)
                assertTrue(field.isValid)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `setFormTextField Should not update promotional code When code is too long`() = runTest {
        viewModel.executeIntent(FormIntent.SetFormTextField(3, "TOOLONGCODE"))
        viewModel.uiState.test {
            assertEquals(String(), awaitItem().fields[3].value)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `formTextFieldUnfocused Should show email error When email is invalid`() = runTest {
        viewModel.executeIntent(FormIntent.SetFormTextField(1, "invalid"))
        viewModel.executeIntent(FormIntent.FormTextFieldFocused(1))
        viewModel.executeIntent(FormIntent.FormTextFieldUnfocused(1))
        viewModel.uiState.test {
            assertEquals(R.string.form_email_error, awaitItem().fields[1].errorMsgId)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `formTextFieldUnfocused Should show promotional code error When code is too short`() = runTest {
        viewModel.executeIntent(FormIntent.SetFormTextField(3, "32"))
        viewModel.executeIntent(FormIntent.FormTextFieldUnfocused(3))
        viewModel.uiState.test {
            assertEquals(R.string.form_promotional_code_error, awaitItem().fields[3].errorMsgId)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `selectedDate Should update delivery date field When valid date is selected`() = runTest {
        viewModel.executeIntent(FormIntent.SelectedDate(LocalDate.of(2025, 6, 30)))
        viewModel.uiState.test {
            awaitItem().fields[4].let { field ->
                assertEquals("30/06/2025", field.value)
                assertTrue(field.isValid)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `setClassification Should update classification field`() = runTest {
        viewModel.executeIntent(FormIntent.SetClassification(FormClassification.EXCELLENT))
        viewModel.uiState.test {
            assertEquals(FormClassification.EXCELLENT, awaitItem().classification)
            cancelAndConsumeRemainingEvents()
        }
    }

    private fun initViewModel() = FormViewModel(
        analyticSender = analyticSender,
        asyncTaskUtils = asyncTaskUtils
    )
}
