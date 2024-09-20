package team.asteria.slay.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import team.asteria.slay.global.jwt.properties.JwtExpTimeProperties
import team.asteria.slay.global.jwt.properties.JwtProperties

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class
    ]
)
class PropertiesScanConfig
