import ru.itmo.mse.simpleton.runner.Executor
import ru.itmo.mse.simpleton.runner.TimerExecutor
import java.io.IOException

fun printErr(s: String?) {
    System.err.print(s)
}

fun main(args: Array<String>) {
    val pyPath =
        when(args.size) {
            0 -> "python3"
            1 -> args[0]
            else -> {
                printErr("Expected 0 or 1 argument but given ${args.size}")
                return
            }
        }

    try {
        val executor: Executor = TimerExecutor()
        executor.execute(pyPath, "-m", "timeit", "-r", "10")
    } catch (e: IOException) {
        printErr(e.message)
    }
}