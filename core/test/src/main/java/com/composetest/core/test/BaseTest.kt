package com.composetest.core.test

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.BeforeAll

abstract class BaseTest : CoroutinesTest {
    private companion object {
        @BeforeAll
        @JvmStatic
        fun setupStaticMocks() {
            mockkStatic(Log::class)
            every { Log.e(any(), any(), any()) } returns 0
        }
    }
}