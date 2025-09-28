package knob

import java.io.File
import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Paths
import java.security.MessageDigest
import kotlin.io.path.div
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.host.ScriptingHostConfiguration
import kotlin.script.experimental.jvm.compilationCache
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.CompiledScriptJarsCache

// kotlin-main-kts -> scriptDef.kt -> COMPILED_SCRIPTS_CACHE_VERSION
private const val IDK_RANDOM_VALUE = 1

object KnobHostConfiguration : ScriptingHostConfiguration(
    {
        jvm {
            val cacheDir = Paths.get(".") /
                ".knob" /
                "cache" /
                "knob"

            Files.createDirectories(cacheDir)

            compilationCache(
                CompiledScriptJarsCache { script, configuration ->
                    val mangled = mangle(script, configuration)
                    File(cacheDir.toFile(), mangled)
                },
            )
        }
    },
)

private fun mangle(
    script: SourceCode,
    configuration: ScriptCompilationConfiguration,
): String {
    val digestWrapper = MessageDigest.getInstance("SHA-256")

    fun addToDigest(chunk: String) = with(digestWrapper) {
        val chunkBytes = chunk.toByteArray()
        update(chunkBytes.size.toByteArray())
        update(chunkBytes)
    }

    digestWrapper.update(IDK_RANDOM_VALUE.toByteArray())
    addToDigest(script.text)
    configuration.notTransientData.entries
        .sortedBy { it.key.name }
        .forEach {
            addToDigest(it.key.name)
            addToDigest(it.value.toString())
        }
    return digestWrapper.digest().toHexString()
}

private fun Int.toByteArray(): ByteArray {
    val buffer = ByteBuffer.allocate(Int.SIZE_BYTES)
    buffer.putInt(this)
    return buffer.array()
}
