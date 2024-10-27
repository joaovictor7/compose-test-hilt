package com.composetest.core.router.interfaces

import androidx.navigation.NavType
import kotlin.reflect.KType

@PublishedApi
internal interface NavType {
    val navTypes: Map<KType, NavType<*>>
}