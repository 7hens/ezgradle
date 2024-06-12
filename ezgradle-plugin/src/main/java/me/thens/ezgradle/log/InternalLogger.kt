package me.thens.ezgradle.log

import dev.horizona.core.log.Logger

internal object InternalLogger {
    var logger = Logger(tag = "ezgradle")

    fun update(fn: Logger.() -> Logger) {
        logger = fn(logger)
    }
}

internal val Any.log get() = InternalLogger.logger.tag(this)
