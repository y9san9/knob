package knob

/**
 * Default Kotlin Compiler Accessor
 */
public val kotlinc: Kotlinc = Kotlinc()

/**
 * Type-safe wrapper around Kotlin Compiler.
 */
public class Kotlinc(public val name: String = "kotlinc") {

    public suspend fun compile(
        vararg source: String,
        destination: String = ".knob/classpath",
    ): String? {
        val files = if (source.isEmpty()) {
            arrayOf(".")
        } else {
            source
        }
        val options = buildList {
            add(name)
            add("-d")
            add(destination)
            addAll(files)
        }
        return execute(options)
    }
}
