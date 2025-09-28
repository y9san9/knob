package knob

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

/**
 * [knob] is used to access Knob DSL. This DSL is intended to help with
 * bootstrapping your project.
 */
public fun setup(args: List<String>, block: suspend Knob.() -> Unit) {
    runBlocking {
        val scope = Knob(args, scope = this)
        scope.block()
    }
}

/**
 * [knob] is used to access Knob DSL. This DSL is intended to help with
 * bootstrapping your project.
 */
public fun setup(args: Array<String>, block: suspend Knob.() -> Unit) {
    setup(args.toList(), block)
}

/**
 * [Knob] defined main methods that one may want to use to bootstrap
 * project compilation / running.
 */
public class Knob(
    public val args: List<String>,
    scope: CoroutineScope,
    public val kotlin: Kotlin = Kotlin("kotlin"),
    public val kotlinc: Kotlinc = Kotlinc("kotlinc"),
) : CoroutineScope by scope {
    public val command: String? = args.getOrNull(0)
}

/**
 * [CommandScope] overrides args from [Knob] by stripping command name.
 */
public class CommandScope(public val args: List<String>)
