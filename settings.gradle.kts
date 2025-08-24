pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeTest"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":common:androidapi")
include(":common:api")
include(":core:test:androidapi")
include(":core:test:api")
include(":core:router")
include(":core:designsystem")
include(":core:ui")
include(":core:data:androidapi")
include(":core:data:api")
include(":core:data:impl")
include(":core:domain")
include(":core:database:androidapi")
include(":core:database:impl")
include(":core:security")
include(":core:security:api")
include(":core:security:androidapi")
include(":core:security:impl")
include(":core:network")
include(":core:analytic:api")
include(":core:analytic:impl")
include(":feature:login")
include(":feature:root")
include(":feature:home")
include(":feature:configuration")
include(":feature:account")
include(":feature:weatherforecast")
include(":feature:news")
include(":feature:exchange")
include(":feature:form")
include(":feature:product")
include(":feature:findroute")
