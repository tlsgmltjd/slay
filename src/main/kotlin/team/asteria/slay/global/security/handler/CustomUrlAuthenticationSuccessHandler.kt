package team.asteria.slay.global.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import team.asteria.slay.global.jwt.TokenGenerator

@Component
class CustomUrlAuthenticationSuccessHandler(
    private val tokenGenerator: TokenGenerator
): AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val userId = authentication.name.toLong()
        val accessToken = tokenGenerator.generateAccessToken(userId)
        response.setHeader("Authorization", accessToken)
    }

}
