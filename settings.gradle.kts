enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "knob-root"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("knob")
