package team.asteria.slay.domain.user.persistence

import org.springframework.stereotype.Component
import team.asteria.slay.domain.user.domain.User
import team.asteria.slay.domain.user.mapper.UserMapper
import team.asteria.slay.domain.user.persistence.repository.UserRepository
import team.asteria.slay.domain.user.port.out.UserPort

@Component
class UserPersistenceAdapter(
    private val userMapper: UserMapper,
    private val userRepository: UserRepository
): UserPort {
    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)?.let { userMapper.toDomain(it) }
    }

    override fun save(user: User): User {
        val userEntity = userMapper.toEntity(user)
        val savedUserEntity = userRepository.save(userEntity)

        return userMapper.toDomain(savedUserEntity)
    }
}
