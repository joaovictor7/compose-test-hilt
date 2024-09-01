package com.composetest.core.domain.usecases

import com.composetest.core.domain.enums.DockItem
import javax.inject.Inject

class ChangeDockItemSelectionUseCase @Inject constructor() {

    private val dockItemsOrder = mutableListOf(firstSelectedDockItem)

    operator fun invoke(selectedDockItemIndex: Int) =
        DockItem.getItemDock(selectedDockItemIndex).also {
            if (it == firstSelectedDockItem) {
                dockItemsOrder.clear()
            } else {
                dockItemsOrder.remove(it)
            }
            dockItemsOrder.add(it)
        }

    operator fun invoke() =
        if (dockItemsOrder.size > 1) dockItemsOrder[dockItemsOrder.lastIndex - 1].also {
            dockItemsOrder.removeLastOrNull()
        } else null

    private companion object {
        val firstSelectedDockItem = DockItem.HOME
    }
}