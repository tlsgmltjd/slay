package team.asteria.slay.infra.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import team.asteria.slay.infra.feign.dto.GoogleInfoResDto
import team.asteria.slay.infra.feign.dto.GoogleTokenResDto


@FeignClient(name = "GoogleOauthTokenClient", url = "\${oauth.google.token-url}")
interface GoogleOauthTokenFeignClient {
    @PostMapping("/token")
    fun getToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("redirect_uri") redirectUrl: String,
        @RequestParam("code") code: String,
        @RequestParam("grant_type") grantType: String
    ): GoogleTokenResDto
}
