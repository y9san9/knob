plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.maven.publish) apply false
    alias(libs.plugins.ktlint) apply false
}

tasks {
    val printVersion by registering {
        group = "CI"

        doFirst {
            println(libs.versions.knob.get())
        }
    }
}
