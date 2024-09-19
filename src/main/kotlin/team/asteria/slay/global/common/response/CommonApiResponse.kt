package team.asteria.slay.global.common.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import jakarta.annotation.Nonnull
import lombok.NonNull
import org.springframework.http.HttpStatus

data class CommonApiResponse<T>(
    val status: HttpStatus,
    val code: Int,
    val message: String?,
    @JsonInclude(NON_NULL)
    val data: T? = null
) {
    companion object {
        fun success(@NonNull message: String): CommonApiResponse<Nothing?> {
            return CommonApiResponse(HttpStatus.OK, HttpStatus.OK.value(), message, null)
        }

        fun created(@NonNull message: String): CommonApiResponse<Nothing?> {
            return CommonApiResponse(HttpStatus.CREATED, HttpStatus.CREATED.value(), message, null)
        }

        fun error(message: String?, @Nonnull status: HttpStatus): CommonApiResponse<Nothing?> {
            return CommonApiResponse(status, status.value(), message, null)
        }
    }
}
