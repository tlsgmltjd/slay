package team.asteria.slay.infra.feign

import org.springframework.stereotype.Service
import team.asteria.slay.infra.feign.client.GoogleOauthInfoFeignClient
import team.asteria.slay.infra.feign.dto.GoogleInfoResDto

@Service
class GoogleLoginFeignClientService(
    private val googleOauthInfoFeignClient: GoogleOauthInfoFeignClient,
) {

    fun login(accessToken: String): GoogleInfoResDto {
        val infoDto: GoogleInfoResDto

        try {
            infoDto = getInfo(accessToken)
        } catch (e: Exception) {
            throw RuntimeException("google oauth 사용자 정보를 요청 중 예외가 발생했습니다.")
        }

        return infoDto
    }

    private fun getInfo(accessToken: String) =
        googleOauthInfoFeignClient.getInfo(
            accessToken = "Bearer $accessToken"
        )
}
