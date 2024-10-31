package com.composetest.common.enums

enum class BuildType {
    RELEASE, DEBUG;

    override fun toString() = name.lowercase()

    companion object {
        fun String.getBuildType() = entries.firstOrNull { it.toString() == this } ?: DEBUG
    }
}