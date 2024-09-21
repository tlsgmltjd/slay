package team.asteria.slay.domain.user.port.`in`

import team.asteria.slay.domain.user.presentation.dto.request.LoginReqDto
import team.asteria.slay.domain.user.presentation.dto.response.LoginResDto

interface LoginUseCase {
    fun execute(reqDto: LoginReqDto): LoginResDto
}
