package team.asteria.slay.domain.member.domain

import team.asteria.slay.domain.member.entity.enums.Role

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val role: Role,
    val coin: Int
)
