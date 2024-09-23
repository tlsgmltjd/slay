package team.asteria.slay.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.asteria.slay.domain.user.domain.User
import team.asteria.slay.domain.user.port.`in`.LoginUseCase
import team.asteria.slay.domain.user.port.out.QueryUserPort
import team.asteria.slay.domain.user.port.out.SaveUserPort
import team.asteria.slay.domain.user.presentation.dto.request.LoginReqDto
import team.asteria.slay.domain.user.presentation.dto.response.LoginResDto
import team.asteria.slay.global.jwt.TokenGenerator
import team.asteria.slay.infra.feign.GoogleLoginFeignClientService

@Service
class LoginService(
    private val googleLoginFeignClientService: GoogleLoginFeignClientService,
    private val queryUserPort: QueryUserPort,
    private val saveUserPort: SaveUserPort,
    private val tokenGenerator: TokenGenerator
): LoginUseCase {

    @Transactional
    override fun execute(reqDto: LoginReqDto): LoginResDto {
        val email = googleLoginFeignClientService.login(reqDto.accessToken).email
        val user = findOrSaveUser(email)

        val tokenDto = tokenGenerator.generateToken(user.id!!)

        return LoginResDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken
        )
    }

    private fun findOrSaveUser(email: String) =
        queryUserPort.findByEmail(email) ?: saveUserPort.save(User(email = email))

}
