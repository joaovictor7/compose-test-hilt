package com.composetest.core.router.extensions

import androidx.navigation.toRoute
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.utils.getNavTypes

inline fun <reified D : Destination> NavigationManager.getParam() =
    savedStateHandle.toRoute<D>(getNavTypes<D>())