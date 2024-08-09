plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.database"
}

dependencies {
    implementation(projects.core.security)
    implementation(projects.common)
    implementation(libs.androidx.dataStore)
    implementation(libs.room)
    ksp(libs.room.compile)
}