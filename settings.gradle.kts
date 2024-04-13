pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        //SmoothBottomBar
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //SmoothBottomBar
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "CryptoYar"
include(":app")
