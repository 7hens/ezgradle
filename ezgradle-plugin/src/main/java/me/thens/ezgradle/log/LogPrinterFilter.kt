package dev.horizona.core.log

internal class LogPrinterFilter(
    private val printer: LogPrinter,
    private val filter: LogFilter,
) : LogPrinter {
    override fun print(log: Log) {
        val fossilLog = log.fossil()
        if (filter.filter(fossilLog)) {
            printer.print(fossilLog)
        }
    }
}