package team.asteria.slay.global.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.asteria.slay.global.jwt.dto.TokenDto
import team.asteria.slay.global.jwt.properties.JwtExpTimeProperties
import team.asteria.slay.global.jwt.properties.JwtProperties
import team.asteria.slay.global.jwt.properties.JwtProperties.Companion.ACCESS
import team.asteria.slay.global.jwt.properties.JwtProperties.Companion.REFRESH
import team.asteria.slay.global.jwt.properties.JwtProperties.Companion.TOKEN_TYPE
import java.util.*

@Component
class TokenGenerator(
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties
) {

    fun generateToken(userId: Long): TokenDto =
        TokenDto(
            accessToken = "Bearer ${generateAccessToken(userId)}",
            refreshToken = "Bearer ${generateRefreshToken(userId)}",
        )

    fun generateAccessToken(userId: Long): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim(TOKEN_TYPE, ACCESS)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp * 1000))
            .compact()

    fun generateRefreshToken(userId: Long): String =
        Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim(TOKEN_TYPE, REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.refreshExp * 1000))
            .compact()
}
