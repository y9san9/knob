package knob

import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    compilationConfiguration = KnobCompilationConfiguration::class,
    evaluationConfiguration = KnobEvaluationConfiguration::class,
    hostConfiguration = KnobHostConfiguration::class,
)
abstract class KnobScript(args: Array<String>) {
    val args: List<String> = args.toList()

    fun knob(block: suspend Knob.() -> Unit) {
        setup(args, block)
    }
}
