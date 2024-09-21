package team.asteria.slay.global.jwt.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "oauth.google")
class OauthProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUrl: String,
)
