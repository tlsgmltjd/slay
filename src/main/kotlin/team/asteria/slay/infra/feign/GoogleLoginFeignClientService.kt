package team.asteria.slay.infra.feign

import org.springframework.stereotype.Service
import team.asteria.slay.global.jwt.properties.OauthProperties
import team.asteria.slay.infra.feign.client.GoogleOauthInfoFeignClient
import team.asteria.slay.infra.feign.client.GoogleOauthTokenFeignClient
import team.asteria.slay.infra.feign.dto.GoogleInfoResDto

@Service
class GoogleLoginFeignClientService(
    private val googleOauthTokenFeignClient: GoogleOauthTokenFeignClient,
    private val googleOauthInfoFeignClient: GoogleOauthInfoFeignClient,
    private val oauthProperties: OauthProperties
) {
    fun login(code: String): GoogleInfoResDto {
        val tokenDto = getToken(code)
        return getInfo(tokenDto.accessToken)
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
