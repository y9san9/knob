package knob

/**
 * [knob] is used to access Knob DSL. This DSL is intended to help with
 * bootstrapping your project.
 */
public inline fun knob(args: Array<String>, block: KnobScope.() -> Unit) {
    val scope = KnobScope()
    scope.apply(block)
    println(args)
}

/**
 * [KnobScope] defined main methods that one may want to use to bootstrap
 * project compilation / running.
 */
public class KnobScope
