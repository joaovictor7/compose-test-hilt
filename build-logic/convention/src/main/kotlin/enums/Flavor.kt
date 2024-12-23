package enums

internal enum class Flavor {
    DEVELOP, STAGING, PRODUCTION;

    val isDefault get() = this == DEVELOP

    override fun toString() = name.lowercase()
}