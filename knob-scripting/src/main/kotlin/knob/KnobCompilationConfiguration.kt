package knob

import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.defaultImports
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm

object KnobCompilationConfiguration : ScriptCompilationConfiguration({
    defaultImports("knob.*", "kotlin.time.*")
    jvm {
        dependenciesFromCurrentContext(wholeClasspath = true)
    }
})
