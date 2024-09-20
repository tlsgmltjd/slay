package team.asteria.slay.global.security.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component

@Component
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf -> csrf.disable() }

        http.sessionManagement { sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.authorizeHttpRequests { authRequest ->
            authRequest
                .requestMatchers("/**").permitAll()
        }

        http.oauth2Login { oauth2Login ->
            oauth2Login.authorizationEndpoint { authorizationEndpoint ->
                authorizationEndpoint.baseUri("/auth/v3/oauth2/authorization")
            }
        }

        return http.build()
    }
}
