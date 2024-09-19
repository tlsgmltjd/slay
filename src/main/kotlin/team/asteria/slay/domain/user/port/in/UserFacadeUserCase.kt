package team.asteria.slay.domain.user.port.`in`

import team.asteria.slay.domain.user.domain.User

interface UserFacadeUserCase {
    fun getUserByEmail(email: String): User
}
