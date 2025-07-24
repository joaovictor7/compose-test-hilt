plugins {
    alias(libs.plugins.composetest.kotlin)
    alias(libs.plugins.composetest.test)
}

dependencies {
    implementation(projects.common)
}