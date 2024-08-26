package com.jurocknsail.util

import jakarta.enterprise.inject.spi.InjectionPoint
import jakarta.ws.rs.Produces
import java.util.logging.Logger


class LoggerProvider {
    @Produces
    fun createLogger(injectionPoint: InjectionPoint): Logger {
        return Logger.getLogger(injectionPoint.member.declaringClass.name)
    }
}