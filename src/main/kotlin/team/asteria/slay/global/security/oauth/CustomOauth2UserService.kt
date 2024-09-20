package team.asteria.slay.global.security.oauth

import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import team.asteria.slay.domain.user.domain.User
import team.asteria.slay.domain.user.entity.enums.Role.*
import team.asteria.slay.domain.user.port.out.QueryUserPort
import team.asteria.slay.domain.user.port.out.SaveUserPort
import java.time.LocalDateTime

@Service
class CustomOauth2UserService(
    private val queryUserPort: QueryUserPort,
    private val saveUserPort: SaveUserPort
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private val delegateOauth2UserService = DefaultOAuth2UserService()

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = delegateOauth2UserService.loadUser(userRequest)
        val oAuthAttributes = oAuth2User.attributes

        val provider = userRequest.clientRegistration.registrationId ?: throw AuthenticationServiceException("OAuth provider가 존재하지 않습니다.")
        val email = oAuthAttributes["email"].toString()

        val user = getUser(email)

        val attributes = mapOf(
            "id" to user.id,
            "role" to user.role,
            "provider" to provider,
            "provider_id" to email,
            "last_login_time" to LocalDateTime.now()
        )

        val authorities = oAuth2User.authorities.toMutableList()
        authorities.add(SimpleGrantedAuthority(user.role.name))

        return UserInfo(authorities, attributes, "id")
    }

    private fun getUser(email: String): User {
        return queryUserPort.findByEmail(email)
            ?: saveUserPort.save(User(email = email))
    }
}
