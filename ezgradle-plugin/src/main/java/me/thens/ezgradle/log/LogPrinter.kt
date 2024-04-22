package dev.horizona.core.log

fun interface LogPrinter {
    fun print(log: Log)

    fun filter(filter: LogFilter): LogPrinter = LogPrinterFilter(this, filter)

    companion object {
        private const val DATE_PATTERN = "HH:mm:ss.SSS"
        val Empty: LogPrinter = LogPrinterEmpty
        val System: LogPrinter = LogPrinterSystem(DATE_PATTERN)
    }
}