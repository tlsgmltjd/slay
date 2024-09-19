package team.asteria.slay.domain.user.port.out

import team.asteria.slay.domain.user.domain.User

interface QueryUserPort {
    fun findByEmail(email: String): User?
}
