package team.asteria.slay.global.exception.error

import org.springframework.http.HttpStatus

class ExpectedException(
    message: String?,
    val statusCode: HttpStatus
) : RuntimeException(message)
