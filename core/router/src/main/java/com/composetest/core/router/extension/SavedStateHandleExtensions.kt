package com.composetest.core.router.extension

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.util.getNavTypes

inline fun <reified D : Destination> SavedStateHandle.getDestination() =
    toRoute<D>(getNavTypes<D>())