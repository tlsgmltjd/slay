package team.asteria.slay.global.security.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import team.asteria.slay.global.filter.JwtRequestFilter
import team.asteria.slay.global.jwt.TokenGenerator

@Component
@EnableWebSecurity
class SecurityConfig(
    private val tokenRequestFilter: JwtRequestFilter,
    private val tokenGenerator: TokenGenerator
) {
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

        http.addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
