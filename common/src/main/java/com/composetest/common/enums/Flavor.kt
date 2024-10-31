package com.composetest.common.enums

enum class Flavor {
    FULL, FREE;

    override fun toString() = name.lowercase()

    companion object {
        fun String.getFlavor() = entries.find { it.toString() == this } ?: FREE
    }
}