plugins {
    alias(libs.plugins.composetest.kotlin.library)
    alias(libs.plugins.composetest.test)
}

dependencies {
    implementation(projects.common.api)
}