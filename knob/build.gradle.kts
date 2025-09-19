import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.ktlint)
}

group = "me.y9san9.knob"

version = libs.versions.knob.get()

kotlin {
    jvmToolchain(8)

    explicitApi()

    compilerOptions {
        extraWarnings = true
        allWarningsAsErrors = true
        progressiveMode = true
    }
}

dependencies {
    api(libs.kotlinx.coroutines)
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
