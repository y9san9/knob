import com.vanniktech.maven.publish.SonatypeHost

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.ktlint)
}

group = "me.y9san9.knob"
version = libs.versions.knob.get()

tasks.distZip {
    archiveFileName = "knob-scripting-latest.zip"
}

kotlin {
    jvmToolchain(8)

    compilerOptions {
        extraWarnings = true
        allWarningsAsErrors = true
        progressiveMode = true
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

    pom {
        name = "knob"
        description = "Library for writing build recipes in Kotlin."
        url = "https://github.com/y9san9/knob"

        licenses {
            license {
                name = "MIT"
                distribution = "repo"
                url = "https://github.com/y9san9/knob/blob/main/LICENSE.md"
            }
        }

        developers {
            developer {
                id = "y9san9"
                name = "Alex Sokol"
                email = "y9san9@gmail.com"
            }
        }

        scm {
            connection = "scm:git:ssh://github.com/y9san9/knob.git"
            developerConnection = "scm:git:ssh://github.com/y9san9/knob.git"
            url = "https://github.com/y9san9/knob"
        }
    }

    signAllPublications()
}

dependencies {
    api(projects.knob)
    api(libs.kotlinx.coroutines)
    implementation(libs.kotlin.scripting.common)
    implementation(libs.kotlin.scripting.jvm)
    implementation(libs.kotlin.scripting.jvmhost)
}
