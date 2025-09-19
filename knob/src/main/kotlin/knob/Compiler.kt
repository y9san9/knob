package knob

/**
 * Type-safe wrapper around Kotlin Compiler.
 */
public class Compiler(public val name: String = "kotlinc") {

    public suspend fun build(vararg source: String) {
        val files = if (source.isEmpty()) {
            arrayOf("**.kt")
        } else {
            source
        }
        execute(name, *files)
    }
}
