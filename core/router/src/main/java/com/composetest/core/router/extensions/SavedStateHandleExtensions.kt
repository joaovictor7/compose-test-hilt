package com.composetest.core.router.extensions

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.utils.getNavTypes

inline fun <reified D : Destination> SavedStateHandle.getDestination() =
    toRoute<D>(getNavTypes<D>())