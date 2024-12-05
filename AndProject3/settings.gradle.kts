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

rootProject.name = "AndProject3"
include(":app")
include(":andtablaymenudlg")
include(":andactivitytest1")
include(":andactivitytest2")
include(":andlistview1")
include(":andlistview2")
include(":andrecytest1")
include(":anddb_recy")
