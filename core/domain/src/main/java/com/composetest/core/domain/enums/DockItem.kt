package com.composetest.core.domain.enums

enum class DockItem {
    HOME,
    CONFIGURATION,
    HOME2;

    companion object {
        fun getItemDock(index: Int) = entries[index]
        fun getItemDockIndex(dockItem: DockItem) = entries.indexOf(dockItem)
    }
}