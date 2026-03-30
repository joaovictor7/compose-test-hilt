package com.composetest.core.router.extension

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.util.getNavTypes

inline fun <reified D : NavKey> SavedStateHandle.getNavKey(): D =
    toRoute<D>(getNavTypes<D>())