package team.asteria.slay.infra.feign

import org.springframework.stereotype.Service
import team.asteria.slay.global.jwt.properties.OauthProperties
import team.asteria.slay.infra.feign.client.GoogleOauthInfoFeignClient
import team.asteria.slay.infra.feign.client.GoogleOauthTokenFeignClient
import team.asteria.slay.infra.feign.dto.GoogleInfoResDto
import team.asteria.slay.infra.feign.dto.GoogleTokenResDto

@Service
class GoogleLoginFeignClientService(
    private val googleOauthTokenFeignClient: GoogleOauthTokenFeignClient,
    private val googleOauthInfoFeignClient: GoogleOauthInfoFeignClient,
    private val oauthProperties: OauthProperties
) {

    fun login(code: String): GoogleInfoResDto {
        val tokenDto: GoogleTokenResDto
        val infoDto: GoogleInfoResDto

        try {
            tokenDto = getToken(code)
        } catch (e: Exception) {
            throw RuntimeException("google oauth access_token 정보를 요청 중 예외가 발생했습니다.")
        }

        try {
            infoDto = getInfo(tokenDto.accessToken)
        } catch (e: Exception) {
            throw RuntimeException("google oauth 사용자 정보를 요청 중 예외가 발생했습니다.")
        }

        return infoDto
    }

    private fun getToken(code: String) =
        googleOauthTokenFeignClient.getToken(
            clientId = oauthProperties.clientId,
            clientSecret = oauthProperties.clientSecret,
            redirectUrl = oauthProperties.redirectUrl,
            code = code,
            grantType = "authorization_code"
        )

    private fun getInfo(accessToken: String) =
        googleOauthInfoFeignClient.getInfo(
            accessToken = "Bearer $accessToken"
        )
}
