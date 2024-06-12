package dev.horizona.core.log

enum class LogLevel { VERBOSE, DEBUG, INFO, WARN, ERROR, NEVER }

typealias MessageProvider = () -> String?

data class Log(
    val level: LogLevel,
    val tag: String,
    val msg: MessageProvider,
) {
    fun fossil() = msg().let { copy(msg = { it }) }
}
