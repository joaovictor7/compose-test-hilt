plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.domain"
}

dependencies {
    implementation(projects.core.security)
    implementation(projects.common)
}