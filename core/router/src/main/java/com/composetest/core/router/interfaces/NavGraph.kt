package com.composetest.core.router.interfaces

import androidx.navigation.NavGraphBuilder
import kotlin.reflect.KProperty

interface NavGraph {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): List<NavGraphBuilder.() -> Unit>
}