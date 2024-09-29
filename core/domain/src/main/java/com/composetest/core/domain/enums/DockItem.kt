package com.composetest.core.domain.enums

enum class DockItem {
    HOME,
    PROFILE,
    CONFIGURATION;

    companion object {
        fun getItemDock(index: Int) = entries[index]
        fun getItemDockIndex(dockItem: DockItem) = entries.indexOf(dockItem)
    }
}