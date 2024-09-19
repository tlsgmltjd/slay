package team.asteria.slay.global.common.response

import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
class ApiResponseWrapper: ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {

        if (body is CommonApiResponse<*>) {
            return byPassResponse(body, response)
        }

        if (body is Map<*, *>) {
            val bodyMap = body as Map<String, Any?>
            val errorResponse: CommonApiResponse<Nothing?>? = exceptionResponse(response, bodyMap)
            if (errorResponse != null) return errorResponse
        }

        val commonApiResponse: CommonApiResponse<Any?> = CommonApiResponse(
            HttpStatus.OK,
            HttpStatus.OK.value(),
            "OK",
            body
        )

        response.setStatusCode(HttpStatus.OK)
        return commonApiResponse
    }

    private fun byPassResponse(body: Any, response: ServerHttpResponse): Any {
        val commonApiMessageResponse = body as CommonApiResponse<*>
        response.setStatusCode(commonApiMessageResponse.status)
        return body
    }

    private fun exceptionResponse(response: ServerHttpResponse, bodyMap: Map<String, Any?>): CommonApiResponse<Nothing?>? {
        if (bodyMap.containsKey("status")) {
            val statusCode = bodyMap["status"] as Int
            if (statusCode in 400..599) {
                val status = HttpStatus.valueOf(statusCode)
                val errorResponse = CommonApiResponse.error(status.reasonPhrase, status)
                response.setStatusCode(status)
                return errorResponse
            }
        }
        return null
    }
}
