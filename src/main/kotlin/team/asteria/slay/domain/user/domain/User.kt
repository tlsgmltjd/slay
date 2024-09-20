package team.asteria.slay.domain.user.domain

import team.asteria.slay.domain.user.entity.enums.Role
import team.asteria.slay.domain.user.entity.enums.Role.*

data class User(
    val id: Long? = null,
    val name: String? = null,
    val email: String,
    val role: Role = TEMP,
    val coin: Int = 0
)
