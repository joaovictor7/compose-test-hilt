package com.composetest.core.domain.enums

import com.composetest.core.domain.enums.Flavor.entries

enum class Flavor {
    DEVELOP, STAGING, PRODUCTION;

    override fun toString() = name.lowercase()

    companion object {
        fun String.getFlavor() = entries.find { it.toString() == this } ?: DEVELOP
    }
}