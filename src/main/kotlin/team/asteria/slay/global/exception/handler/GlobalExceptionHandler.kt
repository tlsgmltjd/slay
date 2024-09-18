package team.asteria.slay.global.exception.handler

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.asteria.slay.global.exception.error.ExpectedException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ExpectedException::class)
    fun expectedExceptionHandler(request: HttpServletRequest, ex: ExpectedException): ResponseEntity<Map<String, String>> {
        val message = ex.message ?: "No message available"
        return ResponseEntity.status(ex.statusCode.value()).body(mapOf("message : " to message))
    }
}
