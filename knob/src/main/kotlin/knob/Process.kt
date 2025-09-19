package knob

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.ProcessBuilder
import java.lang.Process as JavaProcess

public class Process(private val java: JavaProcess) {
    public suspend fun wait(): Int = withContext(Dispatchers.IO) {
        java.waitFor()
    }

    public companion object {
        public fun start(vararg args: String): Process {
            val process = ProcessBuilder(*args).start()
            return Process(process)
        }
    }
}

public suspend fun execute(vararg args: String) {
    val process = Process.start(args = args)
    check(process.wait() == 0) {
        "Process finished with non-zero exit code: ${process.wait()}"
    }
}
