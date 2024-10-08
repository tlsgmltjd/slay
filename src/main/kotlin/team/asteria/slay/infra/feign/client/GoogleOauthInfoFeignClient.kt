package team.asteria.slay.infra.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import team.asteria.slay.infra.feign.dto.GoogleInfoResDto


@FeignClient(name = "GoogleOauthInfoClient", url = "\${oauth.google.info-url}")
interface GoogleOauthInfoFeignClient {
    @GetMapping("/oauth2/v1/userinfo")
    fun getInfo(
        @RequestHeader("Authorization") accessToken: String
    ): GoogleInfoResDto
}
