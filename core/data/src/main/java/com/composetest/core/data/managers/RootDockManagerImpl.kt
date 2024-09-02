package com.composetest.core.data.managers

import com.composetest.core.domain.enums.DockItem
import com.composetest.core.domain.managers.RootDockManager
import javax.inject.Inject

internal class RootDockManagerImpl @Inject constructor() : RootDockManager {

    private val dockItemsOrder = mutableListOf(firstSelectedDockItem)

    override fun changeSelectedDockItem(selectedDockItemIndex: Int) =
        DockItem.getItemDock(selectedDockItemIndex).also {
            if (it == firstSelectedDockItem) {
                dockItemsOrder.clear()
            } else {
                dockItemsOrder.remove(it)
            }
            dockItemsOrder.add(it)
        }

    override fun getNextDockItem() =
        if (dockItemsOrder.size > 1) dockItemsOrder[dockItemsOrder.lastIndex - 1].also {
            dockItemsOrder.removeLastOrNull()
        } else null

    private companion object {
        val firstSelectedDockItem = DockItem.HOME
    }
}