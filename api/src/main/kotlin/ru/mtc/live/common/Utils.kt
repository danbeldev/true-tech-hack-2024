package ru.mtc.live.common

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

object Utils {

    fun getCurrentServerAddress(): String {
        val requestAttributes = RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes
        val request = requestAttributes?.request
        val scheme = request?.scheme ?: "http"
        val serverName = request?.serverName ?: "localhost"
        val port = request?.serverPort ?: 8080
        val contextPath = request?.contextPath ?: ""
        return "$scheme://$serverName:$port$contextPath"
    }
}