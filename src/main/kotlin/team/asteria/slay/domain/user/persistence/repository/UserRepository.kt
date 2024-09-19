package team.asteria.slay.domain.user.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.asteria.slay.domain.user.entity.UserJpaEntity

interface UserRepository: JpaRepository<UserJpaEntity, Long> {
    fun findByEmail(email: String): UserJpaEntity?
}
