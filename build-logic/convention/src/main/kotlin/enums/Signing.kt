package enums

internal enum class Signing(
    val signingName: String,
    val buildTypes: List<BuildType>
) {
    RELEASE("release", listOf(BuildType.RELEASE)),
    DEBUG("debug", listOf(BuildType.DEBUG));

    companion object {
        fun getAssociatedBuildType(buildType: BuildType) = values().find {
            buildType in it.buildTypes
        }
    }
}