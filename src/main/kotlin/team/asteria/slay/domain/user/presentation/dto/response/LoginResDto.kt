package team.asteria.slay.domain.user.presentation.dto.response

data class LoginResDto (
    val accessToken: String,
    val refreshToken: String
)
