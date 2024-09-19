package team.asteria.slay.domain.user.facade

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import team.asteria.slay.domain.user.domain.User
import team.asteria.slay.domain.user.port.`in`.UserFacadeUserCase
import team.asteria.slay.domain.user.port.out.QueryUserPort
import team.asteria.slay.global.exception.error.ExpectedException

@Component
class UserFacade(
    private val queryUserPort: QueryUserPort
): UserFacadeUserCase {
    override fun getUserByEmail(email: String): User {
        return queryUserPort.findByEmail(email) ?: throw ExpectedException("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND)
    }
}
