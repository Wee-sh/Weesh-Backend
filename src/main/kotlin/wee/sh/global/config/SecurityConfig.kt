package wee.sh.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import wee.sh.global.error.CustomAuthenticationEntryPoint
import wee.sh.global.error.GlobalExceptionFilter
import wee.sh.global.security.jwt.JwtFilter

@Configuration
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val objectMapper: ObjectMapper,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            } // 세션 기반 인증 안함
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
            } // 401 처리 이 프로젝트에서는 role이 없어서 403 핸들러는 만들지 않음
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/auth/**"
                ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)

        return http.build()
    }
}
