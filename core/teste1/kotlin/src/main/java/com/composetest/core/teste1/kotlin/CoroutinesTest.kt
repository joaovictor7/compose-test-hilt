package com.composetest.core.teste1.kotlin

import com.composetest.core.teste1.kotlin.extension.CoroutinesExtension
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesExtension::class)
interface CoroutinesTest {
    var testDispatcher: TestDispatcher
}