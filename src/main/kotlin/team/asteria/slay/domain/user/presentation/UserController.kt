package team.asteria.slay.domain.user.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.asteria.slay.domain.user.port.`in`.LoginUseCase
import team.asteria.slay.domain.user.presentation.dto.request.LoginReqDto

@RestController
@RequestMapping("/users")
class UserController(
    private val loginUseCase: LoginUseCase
) {
    @PostMapping("/login")
    fun login(@RequestBody loginReqDto: LoginReqDto) =
        loginUseCase.execute(loginReqDto)
}
