package com.ticketera.ticketera.exceptions

import org.springframework.web.bind.annotation.RestControllerAdvice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.http.HttpStatus

@RestControllerAdvice
class ErrorHandling {

    data class ErrorResponse(
        val status: Int,
        val error: String,
        val message: String,
        val path: String
    )

    @ExceptionHandler(TicketeraException::class)
    fun handleTicketeraException(
        ex: TicketeraException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = ex.error.name,
            message = ex.error.message,
            path = request.getDescription(false).removePrefix("uri=")
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val response = ErrorResponse(
            status = status.value(),
            error = status.reasonPhrase,
            message = ex.localizedMessage ?: "Unexpected error",
            path = request.getDescription(false).removePrefix("uri=")
        )
        return ResponseEntity.status(status).body(response)
    }
}
