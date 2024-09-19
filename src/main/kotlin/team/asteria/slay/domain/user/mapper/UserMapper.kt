package team.asteria.slay.domain.user.mapper

import org.springframework.stereotype.Component
import team.asteria.slay.domain.user.domain.User
import team.asteria.slay.domain.user.entity.UserJpaEntity

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
