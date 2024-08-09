plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.domain"
}

dependencies {
    implementation(projects.common)
}