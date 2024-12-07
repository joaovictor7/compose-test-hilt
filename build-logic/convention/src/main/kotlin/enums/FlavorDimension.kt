package enums

internal enum class FlavorDimension(val flavors: List<Flavor>) {
    ENVIRONMENT(listOf(Flavor.DEVELOP, Flavor.STAGING, Flavor.PRODUCTION)),
    DISTRIBUTION(listOf(Flavor.FULL, Flavor.FREE));

    override fun toString() = name.lowercase()

    companion object {
        val allDimensions get() = values().map { it.toString() }
    }
}