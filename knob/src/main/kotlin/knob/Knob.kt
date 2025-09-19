package knob

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

/**
 * [knob] is used to access Knob DSL. This DSL is intended to help with
 * bootstrapping your project.
 */
public fun knob(args: Array<String>, block: KnobScope.() -> Unit) {
    runBlocking {
        val scope = KnobScope(args.toList(), scope = this)
        scope.apply(block)
    }
}

/**
 * [KnobScope] defined main methods that one may want to use to bootstrap
 * project compilation / running.
 */
public class KnobScope(public val args: List<String>, scope: CoroutineScope) :
    CoroutineScope by scope {

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
