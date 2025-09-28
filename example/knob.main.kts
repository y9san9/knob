#!/usr/bin/env kotlin
@file:DependsOn("me.y9san9.knob:knob:1.0.0-dev005")

import knob.*

knob(args) {
    on("build") {
        compiler.build()
    }
}
