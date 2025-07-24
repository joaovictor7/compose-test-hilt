plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.database"
}

dependencies {
    implementation(projects.core.security.api)
    implementation(projects.core.domain)
    implementation(projects.core.data.androidapi)
    implementation(projects.common)
    implementation(libs.room.runtime)
    implementation(libs.room.kotlin)
    implementation(libs.sql.cipher)
    implementation(libs.sqlite)
    ksp(libs.room.compiler)
}