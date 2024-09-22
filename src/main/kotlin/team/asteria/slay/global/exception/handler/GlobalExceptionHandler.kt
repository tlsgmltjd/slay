package team.asteria.slay.global.exception.handler

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.asteria.slay.global.common.response.CommonApiResponse
import team.asteria.slay.global.exception.error.ExpectedException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ExpectedException::class)
    fun expectedExceptionHandler(request: HttpServletRequest, ex: ExpectedException): CommonApiResponse<Nothing?> {
        return CommonApiResponse.error(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RuntimeException::class)
    fun unExpectedExceptionHandler(request: HttpServletRequest, ex: RuntimeException): CommonApiResponse<Nothing?> {
        return CommonApiResponse.error(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
