package enums

internal enum class BuildType(val isInternal: Boolean = false, val isDebuggable: Boolean = true) {
    RELEASE(isInternal = true, isDebuggable = false),
    DEBUG(isInternal = true);

    val isDefault get() = this == DEBUG

    override fun toString() = name.lowercase()
}