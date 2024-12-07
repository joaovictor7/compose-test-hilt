package enums

internal enum class Flavor {
    DEVELOP, STAGING, PRODUCTION, FULL, FREE;

    val isDefault get() = this == FULL

    override fun toString() = name.lowercase()
}