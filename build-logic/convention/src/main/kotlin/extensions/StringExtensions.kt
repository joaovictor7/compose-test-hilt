package extensions

internal val String?.orEmptyToBuildConfigField get() = this ?: "\"\""