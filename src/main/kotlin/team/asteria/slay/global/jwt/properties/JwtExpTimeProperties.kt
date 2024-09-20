package team.asteria.slay.global.jwt.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt.time")
class JwtExpTimeProperties(
    val accessExp: Int,
    val refreshExp: Int
)
