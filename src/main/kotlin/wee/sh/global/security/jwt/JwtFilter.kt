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
        try {
            val token = jwtTokenProvider.resolveToken(request)

            if (token != null) {
                val authentication = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = authentication
                log.debug(
                    "Set Authentication to security context for '{}', uri: {}",
                    authentication.name,
                    request.requestURI
                )
            }
        } catch (e: ExpiredTokenException) {
            log.debug("Expired JWT token: {}", request.requestURI)
            request.setAttribute("exception", e)
        } catch (e: InvalidTokenException) {
            log.debug("Invalid JWT token: {}", request.requestURI)
            request.setAttribute("exception", e)
        } catch (e: Exception) {
            log.error("Could not set user authentication in security context", e)
            request.setAttribute("exception", e)
        }

        filterChain.doFilter(request, response)
    }
}
