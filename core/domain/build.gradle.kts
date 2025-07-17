plugins {
    alias(libs.plugins.composeTest.kotlin)
    alias(libs.plugins.composeTest.test)
}

dependencies {
    implementation(projects.common)
}