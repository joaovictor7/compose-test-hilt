package com.composetest.feature.product.presenter.ui.form

import android.text.TextUtils
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.test.android.BaseTest
import com.composetest.core.test.kotlin.extension.runFlowTest
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.form.R
import com.composetest.feature.form.analytic.screen.FormScreenAnalytic
import com.composetest.feature.form.domain.emuns.FormClassification
import com.composetest.feature.form.presenter.ui.form.viewmodel.FormIntent
import com.composetest.feature.form.presenter.ui.form.viewmodel.FormViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
    fun `init Should send open screen analytic`() = runFlowTest(
        flow = viewModel.uiState,
        onVerify = {
            coVerify { analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(FormScreenAnalytic)) }
        }
    )

    @Test
    fun `setFormTextField Should update email field When email is valid`() = runFlowTest(
        flow = viewModel.uiState,
        onTrigger = {
            viewModel.executeIntent(FormIntent.SetFormTextField(1, "user@email.com"))
        },
        onVerify = {
            val updated = it.last().fields[1]
            assertEquals("user@email.com", updated.value)
            assertTrue(updated.isValid)
        }
    )

    fun `setFormTextField Should update phone field When input is numeric`() = runFlowTest(
        flow = viewModel.uiState,
        onSetup = {
            mockkStatic(TextUtils::class)
            every { TextUtils.isDigitsOnly(any()) } returns true
        },
        onTrigger = {
            viewModel.executeIntent(FormIntent.SetFormTextField(2, "999999999"))
        },
        onVerify = {
            val updated = it.last().fields[2]
            assertEquals("999999999", updated.value)
            assertTrue(updated.isValid)
        }
    )

    @Test
    fun `setFormTextField Should not update promotional code When code is too long`() = runFlowTest(
        flow = viewModel.uiState,
        onTrigger = {
            viewModel.executeIntent(FormIntent.SetFormTextField(3, "TOOLONGCODE"))
        },
        onVerify = {
            assertEquals(String(), it.last().fields[3].value)
        }
    )

    @Test
    fun `formTextFieldUnfocused Should show email error When email is invalid`() = runFlowTest(
        flow = viewModel.uiState,
        onTrigger = {
            viewModel.executeIntent(FormIntent.SetFormTextField(1, "invalid"))
            viewModel.executeIntent(FormIntent.FormTextFieldFocused(1))
            viewModel.formTextFieldUnfocused(1)
        },
        onVerify = {
            assertEquals(R.string.form_email_error, it.last().fields[1].errorMsgId)
        }
    )

    @Test
    fun `formTextFieldUnfocused Should show promotional code error When code is too short`() =
        runFlowTest(
            flow = viewModel.uiState,
            onTrigger = {
                viewModel.executeIntent(FormIntent.SetFormTextField(3, "32"))
                viewModel.executeIntent(FormIntent.FormTextFieldUnfocused(3))
            },
            onVerify = {
                assertEquals(R.string.form_promotional_code_error, it.last().fields[3].errorMsgId)
            }
        )

    @Test
    fun `selectedDate Should update delivery date field When valid date is selected`() =
        runFlowTest(
            flow = viewModel.uiState,
            onTrigger = {
                viewModel.executeIntent(FormIntent.SelectedDate(LocalDate.of(2025, 6, 30)))
            },
            onVerify = {
                assertEquals("30/06/2025", it.last().fields[4].value)
                assertTrue(it.last().fields[4].isValid)
            }
        )

    @Test
    fun `setClassification Should update classification field`() = runFlowTest(
            flow = viewModel.uiState,
            onTrigger = {
                viewModel.executeIntent(FormIntent.SetClassification(FormClassification.EXCELLENT))
            },
            onVerify = {
                assertEquals(FormClassification.EXCELLENT, it.last().classification)
            }
        )

//    @Test
//    fun `submitForm Should emit success dialog`() = runFlowTest(
//        flow = viewModel.uiState,
//        onTrigger = {
//            viewModel.executeIntent(FormIntent.SubmitForm)
//        },
//        onVerify = {
//            assertEquals(FormSimpleDialogParam.Success, states.last().simpleDialogParam)
//        }
//    )

//    @Test
//    fun `dismissSimpleDialog Should clear dialog param`() = runFlowTest(
//        flow = viewModel.uiState,
//        onTrigger = {
//            viewModel.executeIntent(FormIntent.SubmitForm)
//            viewModel.executeIntent(FormIntent.DismissSimpleDialog)
//        },
//        onVerify = {
//            assertNull(it.last().simpleDialogParam)
//        }
//    )

    private fun initViewModel() = FormViewModel(
        analyticSender = analyticSender,
        asyncTaskUtils = asyncTaskUtils
    )
}
