package com.composetest.core.domain.managers

import com.composetest.core.domain.enums.DockItem

interface RootDockManager {
    fun changeSelectedDockItem(selectedDockItemIndex: Int): DockItem

    fun getNextDockItem(): DockItem?
}