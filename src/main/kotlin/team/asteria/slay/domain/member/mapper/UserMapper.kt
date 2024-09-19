package team.asteria.slay.domain.member.mapper

import org.springframework.stereotype.Component
import team.asteria.slay.domain.member.domain.User
import team.asteria.slay.domain.member.entity.UserJpaEntity

@Component
class UserMapper {
    fun toEntity(domain: User) = domain.run {
        UserJpaEntity(
            id = id,
            name = name,
            email = email,
            role = role,
            coin = coin
        )
    }

    fun toDomain(entity: UserJpaEntity) = entity.run {
        User(
            id = id,
            name = name,
            email = email,
            role = role,
            coin = coin
        )
    }
}
