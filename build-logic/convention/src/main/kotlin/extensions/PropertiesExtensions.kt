package extensions

import java.util.Properties

fun Properties?.getPropertyOrEmpty(key: String) = this?.getProperty(key) ?: "\"\""