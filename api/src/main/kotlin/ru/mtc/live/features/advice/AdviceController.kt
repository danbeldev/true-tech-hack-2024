package ru.mtc.live.features.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.mtc.live.common.exceptions.BadRequestException
import ru.mtc.live.common.exceptions.ResourceNotFoundException
import ru.mtc.live.features.advice.model.ExceptionModel
import java.nio.file.AccessDeniedException

@RestControllerAdvice
class AdviceController {

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFound(
        e: ResourceNotFoundException
    ): ExceptionModel {
        return ExceptionModel(e.message, "resource_not_found_error")
    }

    @ExceptionHandler(IllegalStateException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalState(e: IllegalStateException): ExceptionModel {
        return ExceptionModel(e.message, "illegal_state_error")
    }

    @ExceptionHandler(AccessDeniedException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleAccessDenied(e: Exception): ExceptionModel {
        return ExceptionModel(if (e.message == null) "Access denied" else e.message, "access_denied_error")
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException(e: BadRequestException): ExceptionModel {
        return ExceptionModel(e.message, "bad_request_error")
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ExceptionModel {
        e.printStackTrace()
        return ExceptionModel(e.message, "internal_error")
    }
}