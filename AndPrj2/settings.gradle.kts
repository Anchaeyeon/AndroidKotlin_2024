pluginManagement {
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

rootProject.name = "AndPrj2"
include(":app")
include(":linlaytest4")
include(":tabletest")
include(":andlayouttest2")
include(":and_cal_table")
include(":and_grid")
include(":andbmi")
include(":andtimetest1")
include(":andmenutest1")
