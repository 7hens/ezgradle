package dev.horizona.core.log

import me.thens.ezgradle.util.ellipseIfTooLong
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class LogPrinterSystem(private val datePattern: String) : LogPrinter {
    private val dateFormat by lazy { SimpleDateFormat(datePattern, Locale.getDefault()) }

    override fun print(log: Log) {
        val date = dateFormat.format(Date())
        val level = log.level.name.first()
        println("$date $level#${log.tag}:  ${log.msg()?.ellipseIfTooLong()}")
    }
}