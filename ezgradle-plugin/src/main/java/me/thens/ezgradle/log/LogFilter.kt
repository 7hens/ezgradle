package dev.horizona.core.log

fun interface LogFilter {
    fun filter(log: Log): Boolean

    infix fun and(other: LogFilter) = LogFilter { filter(it) && other.filter(it) }

    infix fun or(other: LogFilter) = LogFilter { filter(it) && other.filter(it) }

    fun not() = LogFilter { !filter(it) }

    fun minLevel(level: LogLevel) = and { it.level >= level }

    fun includeTags(vararg tags: String) = and { it.tag in tags }

    fun excludeTags(vararg tags: String) = and { it.tag !in tags }

    companion object : LogFilter {
        override fun filter(log: Log) = true
    }
}