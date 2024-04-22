package dev.horizona.core.log

data class Logger(
    val level: LogLevel = LogLevel.INFO,
    val tag: String = "",
    val printer: LogPrinter = LogPrinter.System,
) {

    val error: Logger get() = level(LogLevel.ERROR)
    val warn: Logger get() = level(LogLevel.WARN)
    val info: Logger get() = level(LogLevel.INFO)
    val debug: Logger get() = level(LogLevel.DEBUG)

    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Only for test")
    val verbose: Logger get() = level(LogLevel.VERBOSE)

    fun level(level: LogLevel) = copy(level = level)

    fun tag(suffix: String) = copy(tag = "$tag:$suffix")

    fun tag(any: Any) = tag(any::class.java.simpleName)

    inline fun <reified T> tag() = tag(T::class.java.simpleName)

    operator fun invoke(msg: MessageProvider): Logger {
        printer.print(Log(level, tag, msg))
        return this
    }

    operator fun invoke(error: Throwable?) = invoke { error?.stackTraceToString() }
    operator fun invoke(msg: Any?) = invoke { msg.toString() }

    fun onlyIf(condition: Boolean) = takeIf { condition } ?: Empty

    companion object {
        val Empty = Logger(printer = LogPrinterEmpty)
    }
}
