package knob

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

/**
 * [knob] is used to access Knob DSL. This DSL is intended to help with
 * bootstrapping your project.
 */
public fun knob(
    args: Array<String>,
    compilerName: String = "kotlinc",
    block: suspend KnobScope.() -> Unit,
) {
    runBlocking {
        val compiler = Compiler(compilerName)
        val scope = KnobScope(compiler, args.toList(), scope = this)
        scope.block()
    }
}

/**
 * [KnobScope] defined main methods that one may want to use to bootstrap
 * project compilation / running.
 */
public class KnobScope(
    public val compiler: Compiler,
    public val args: List<String>,
    scope: CoroutineScope,
) : CoroutineScope by scope {

    public inline fun on(name: String, block: CommandScope.() -> Unit) {
        if (args[0] == name) {
            val command = CommandScope(args.drop(1))
            block(command)
        }
    }
}

/**
 * [CommandScope] overrides args from [KnobScope] by stripping command name.
 */
public class CommandScope(public val args: List<String>)
