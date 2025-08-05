plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.room)
}

android {
    namespace = "com.composetest.core.database.impl"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(projects.core.database.androidapi)
    implementation(projects.core.security.api)
    implementation(projects.core.domain)
    implementation(projects.core.data.androidapi)
    implementation(projects.common.api)
    implementation(libs.room.runtime)
    implementation(libs.room.kotlin)
    implementation(libs.sql.cipher)
    implementation(libs.sqlite)
    ksp(libs.room.compiler)
}