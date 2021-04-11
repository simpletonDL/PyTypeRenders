package ru.itmo.mse.simpleton.runner

interface Executor {
    fun execute(vararg command: String)
}
