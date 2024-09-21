package team.asteria.slay.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import team.asteria.slay.global.jwt.properties.JwtExpTimeProperties
import team.asteria.slay.global.jwt.properties.JwtProperties
import team.asteria.slay.global.jwt.properties.OauthProperties

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class,
        OauthProperties::class
    ]
)
class PropertiesScanConfig
