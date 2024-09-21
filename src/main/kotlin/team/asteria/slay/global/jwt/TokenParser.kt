package team.asteria.slay.global.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import team.asteria.slay.domain.user.port.`in`.UserFacadeUserCase
import team.asteria.slay.global.jwt.properties.JwtProperties
import team.asteria.slay.global.security.auth.AuthDetailsService
import java.security.Key

@Component
class TokenParser(
    private val authDetailsService: AuthDetailsService,
    private val jwtProperties: JwtProperties,
    private val userFacadeUserCase: UserFacadeUserCase
) {

    fun authentication(accessToken: String): UsernamePasswordAuthenticationToken {
        val email = getAccessTokenSubject(accessToken)
        val user = userFacadeUserCase.getUserByEmail(email)
        val authDetails = authDetailsService.loadUserByUsername(user.email)

        return UsernamePasswordAuthenticationToken(authDetails, "", authDetails.authorities)
    }

    private fun getAccessTokenSubject(accessToken: String): String =
        getTokenBody(accessToken.replace("Bearer", ""), jwtProperties.accessSecret).subject

    private fun getTokenBody(token: String, secret: Key): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body
}
