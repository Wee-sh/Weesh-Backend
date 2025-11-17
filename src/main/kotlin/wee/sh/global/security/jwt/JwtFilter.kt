package wee.sh.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import wee.sh.global.security.exception.ExpiredTokenException
import wee.sh.global.security.exception.InvalidTokenException
import wee.sh.global.error.exception.JwtAuthenticationException

@Component
class JwtFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = jwtTokenProvider.resolveToken(request)

        if (token != null) {
            try {
                val authentication = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = authentication
                log.debug(
                    "Set Authentication to security context for '{}', uri: {}",
                    authentication.name,
                    request.requestURI
                )
            } catch (e: ExpiredTokenException) {
                throw JwtAuthenticationException(e.errorCode, e)
            } catch (e: InvalidTokenException) {
                throw JwtAuthenticationException(e.errorCode, e)
            }
        }

        filterChain.doFilter(request, response)
    }
}
