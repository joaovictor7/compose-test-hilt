plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.database"
}

dependencies {
    implementation(projects.core.security)
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(libs.room.runtime)
    implementation(libs.room.kotlin)
    ksp(libs.room.compiler)
}