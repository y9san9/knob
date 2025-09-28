val sources = "src"
val classpath = ".knob/classpath"
val main = "example.MainKt"

knob {
    when (command) {
        "compile" -> compile()
        "run" -> run()
        else -> println("Unknown command option")
    }
}

suspend fun Knob.compile() {
    logCompile {
        val output = kotlinc.compile(sources, destination = classpath)
        if (output != null) {
            println(output.prependIndent("   "))
        }
    }
}

inline fun logCompile(block: () -> Unit) {
    println(">> knob:compile(sources = \"$sources\", destination = \"$classpath\")")
    val compileTime = measureTime {
        block()
    }
    println("<< knob:compile in $compileTime.")
}

suspend fun Knob.run() {
    compile()
    logRun {
        print(kotlin.run(main, classpath))
    }
}

inline fun logRun(block: () -> Unit) {
    println(">> knob:run(main = \"$main\", classpath = \"$classpath\")")
    val runTime = measureTime {
        block()
    }
    println("<< knob:run in $runTime.")
}
