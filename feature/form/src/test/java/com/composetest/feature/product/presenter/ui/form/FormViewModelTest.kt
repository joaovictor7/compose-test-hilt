package com.composetest.feature.product.presenter.ui.form

import android.text.TextUtils
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.test.BaseTest
import com.composetest.core.test.extension.runFlowTest
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.form.R
import com.composetest.feature.form.analytic.screen.FormScreenAnalytic
import com.composetest.feature.form.domain.emuns.FormClassification
import com.composetest.feature.form.presenter.ui.dialog.FormSimpleDialogParam
import com.composetest.feature.form.presenter.ui.form.FormIntent
import com.composetest.feature.form.presenter.ui.form.FormViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull
import java.time.LocalDate

internal class FormViewModelTest : BaseTest() {

    private val analyticSender: AnalyticSender = mockk(relaxed = true)
    private lateinit var viewModel: FormViewModel

    override lateinit var testDispatcher: TestDispatcher

    private val asyncTaskUtils = AsyncTaskUtils(analyticSender, FormScreenAnalytic)

    @BeforeEach
    fun setUp() {
        viewModel = initViewModel()
    }

    @Test
    fun `init Should send open screen analytic`() =
        runFlowTest(viewModel.uiState) { onCancelJob, _ ->
            onCancelJob()
            coVerify { analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(FormScreenAnalytic)) }
        }

    @Test
    fun `setFormTextField Should update email field When email is valid`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SetFormTextField(1, "user@email.com"))
            onCancelJob()
            val updated = states.last().fields[1]
            assertEquals("user@email.com", updated.value)
            assertTrue(updated.isValid)
        }

    @Test
    fun `setFormTextField Should update phone field When input is numeric`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            mockkStatic(TextUtils::class)
            every { TextUtils.isDigitsOnly(any()) } returns true
            viewModel.executeIntent(FormIntent.SetFormTextField(2, "999999999"))
            onCancelJob()
            val updated = states.last().fields[2]
            assertEquals("999999999", updated.value)
            assertTrue(updated.isValid)
        }

    @Test
    fun `setFormTextField Should not update promotional code When code is too long`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SetFormTextField(3, "TOOLONGCODE"))
            onCancelJob()
            assertEquals(String(), states.last().fields[3].value)
        }

    @Test
    fun `formTextFieldUnfocused Should show email error When email is invalid`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SetFormTextField(1, "invalid"))
            viewModel.executeIntent(FormIntent.FormTextFieldFocused(1))
            viewModel.formTextFieldUnfocused(1)

            onCancelJob()
            assertEquals(R.string.form_email_error, states.last().fields[1].errorMsgId)
        }

    @Test
    fun `formTextFieldUnfocused Should show promotional code error When code is too short`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SetFormTextField(3, "32"))
            viewModel.executeIntent(FormIntent.FormTextFieldUnfocused(3))
            onCancelJob()
            assertEquals(R.string.form_promotional_code_error, states.last().fields[3].errorMsgId)
        }

    @Test
    fun `selectedDate Should update delivery date field When valid date is selected`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SelectedDate(LocalDate.of(2025, 6, 30)))
            onCancelJob()
            assertEquals("30/06/2025", states.last().fields[4].value)
            assertTrue(states.last().fields[4].isValid)
        }

    @Test
    fun `setClassification Should update classification field`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SetClassification(FormClassification.EXCELLENT))
            onCancelJob()
            assertEquals(FormClassification.EXCELLENT, states.last().classification)
        }

    @Test
    fun `submitForm Should emit success dialog`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SubmitForm)
            onCancelJob()
            assertEquals(FormSimpleDialogParam.Success, states.last().simpleDialogParam)
        }

    @Test
    fun `dismissSimpleDialog Should clear dialog param`() =
        runFlowTest(viewModel.uiState) { onCancelJob, states ->
            viewModel.executeIntent(FormIntent.SubmitForm)
            viewModel.executeIntent(FormIntent.DismissSimpleDialog)
            onCancelJob()
            assertNull(states.last().simpleDialogParam)
        }

    private fun initViewModel() = FormViewModel(
        analyticSender = analyticSender,
        asyncTaskUtils = asyncTaskUtils
    )
}
