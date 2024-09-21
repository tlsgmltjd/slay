package team.asteria.slay.global.jwt.dto

data class TokenDto (
    val accessToken: String,
    val refreshToken: String,
)
