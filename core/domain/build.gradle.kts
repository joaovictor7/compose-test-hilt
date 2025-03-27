plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.test)
}

android {
    namespace = "com.composetest.core.domain"
}

dependencies {
    implementation(projects.common)
}