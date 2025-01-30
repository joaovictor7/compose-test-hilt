package com.composetest.core.domain.enums

enum class BuildType {
    RELEASE, DEBUG;

    override fun toString() = name.lowercase()

    companion object {
        fun String.getBuildType() = entries.firstOrNull { it.toString() == this } ?: DEBUG
    }
}