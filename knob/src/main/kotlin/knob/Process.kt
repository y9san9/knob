package knob

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.ProcessBuilder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import java.lang.Process as JavaProcess

public class Process(private val java: JavaProcess) {
    private val stdoutReader =
        BufferedReader(InputStreamReader(java.getInputStream()))

    public suspend fun readText(): String = stdoutReader.readText()

    public suspend fun readlnOrNull(): String? = withContext(Dispatchers.IO) {
        stdoutReader.readLine()
    }

    public suspend fun wait(): Int = withContext(Dispatchers.IO) {
        java.waitFor()
    }

    public fun close() {
        stdoutReader.close()
    }

    public companion object {
        public inline fun start(
            vararg args: String,
            block: (Process) -> Unit,
        ) {
            @OptIn(ExperimentalContracts::class)
            contract {
                callsInPlace(block, EXACTLY_ONCE)
            }
            start(args.toList(), block)
        }

        public inline fun start(args: List<String>, block: (Process) -> Unit) {
            @OptIn(ExperimentalContracts::class)
            contract {
                callsInPlace(block, EXACTLY_ONCE)
            }
            val process = ProcessBuilder(args)
                .redirectErrorStream(true)
                .start()
                .let(::Process)
            try {
                block(process)
            } finally {
                process.close()
            }
        }
    }
}

public suspend fun execute(vararg args: String): String = execute(args.toList())

public suspend fun execute(args: List<String>): String {
    Process.start(args = args) { process ->
        val text = process.readText()
        check(process.wait() == 0) {
            "Process finished with non-zero exit code: ${process.wait()}\n$text"
        }
        return text
    }
}
