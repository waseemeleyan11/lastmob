pluginManagement {
    repositories {
        google() // Default Google repository
        mavenCentral() // Default Maven Central repository
        gradlePluginPortal() // Gradle plugin portal for plugins
        maven("https://jitpack.io") // Correct syntax for JitPack in Kotlin DSL
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Default repository for Android dependencies
        mavenCentral() // Default Maven repository
        maven("https://jitpack.io") // Correct syntax for JitPack
    }
}

rootProject.name = "My Application"
include(":app")
