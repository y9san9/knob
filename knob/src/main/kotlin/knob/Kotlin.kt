package knob

/**
 * Default Kotlin Compiler Accessor
 */
public val kotlin: Kotlin = Kotlin()

/**
 * Type-safe wrapper around Kotlin Runner.
 */
public class Kotlin(public val name: String = "kotlin") {
    public suspend fun run(
        subject: String,
        classpath: String = ".knob/classpath",
    ): String? {
        val options = buildList {
            add(name)
            add("-cp")
            add(classpath)
            add(subject)
        }
        return execute(options)
    }
}
