package com.composetest.core.router.util

import androidx.navigation.NavType
import kotlin.reflect.KType
import kotlin.reflect.full.companionObjectInstance
import com.composetest.core.router.interfaces.NavType as NavTypes

inline fun <reified D> getNavTypes(): Map<KType, NavType<*>> =
    (D::class.companionObjectInstance as? NavTypes)?.navTypes ?: emptyMap()