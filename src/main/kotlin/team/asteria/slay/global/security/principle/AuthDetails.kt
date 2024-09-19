package team.asteria.slay.global.security.principle

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import team.asteria.slay.domain.user.domain.User

class AuthDetails(
    private val user: User
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val role = user.role
        val authority = role.name

        val simpleGrantedAuthority = SimpleGrantedAuthority(authority)
        return arrayListOf(simpleGrantedAuthority)
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
