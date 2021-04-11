package ru.itmo.mse.simpleton.runner

import java.util.*

class TimerExecutor : Executor {
    override fun execute(vararg command: String) {
        val process = ProcessBuilder(*command).start()

        val task = object : TimerTask() {
            var counter = 0

            override fun run() {
                println("$counter seconds passed")
                counter += 1
            }
        }

        val timer = Timer()
        timer.schedule(task, 0, 1000)

        val output = process.inputStream.bufferedReader().use { it.readText() }
        println(output)
        timer.cancel()
    }
}