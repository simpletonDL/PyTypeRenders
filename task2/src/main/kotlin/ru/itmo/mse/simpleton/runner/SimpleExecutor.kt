package ru.itmo.mse.simpleton.runner


class SimpleExecutor : Executor {
    private val delayMillis: Long = 1000

    override fun execute(vararg command: String) {
        val process = ProcessBuilder(*command).start()

        var currentMS: Long = 0
        while (process.isAlive) {
            Thread.sleep(delayMillis)
            currentMS += delayMillis
            println("${currentMS / 1000} seconds passed")
        }

        val output = process.inputStream.bufferedReader().use { it.readText() }
        println(output)
    }
}