package team.asteria.slay.global.security.auth

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import team.asteria.slay.domain.user.port.`in`.UserFacadeUserCase

@Service
class AuthDetailsService(
    private val userFacadeUserCase: UserFacadeUserCase
): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userFacadeUserCase.getUserByEmail(email)
        return AuthDetails(user)
    }
}
