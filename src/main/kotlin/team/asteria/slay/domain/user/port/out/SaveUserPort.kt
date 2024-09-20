package team.asteria.slay.domain.user.port.out

import team.asteria.slay.domain.user.domain.User

interface SaveUserPort {
    fun save(user: User): User
}
