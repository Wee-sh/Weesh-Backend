package wee.sh.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import wee.sh.global.error.exception.ErrorCode

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        /**
         * Spring Security의 필터 체인 내부에서 동작하기 때문에
         * WebConfig의 CORS 설정이 적용되지 않음 그래서 여기서 명시적 등록함
         */
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin") ?: "")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS")
        response.setHeader("Access-Control-Allow-Headers", "*")
        response.setHeader("Access-Control-Max-Age", "3600")

        response.status = 401
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        val errorCode = ErrorCode.UNAUTHORIZED
        val errorResponse = ErrorResponse.of(
            errorCode,
            errorCode.message
        )

        objectMapper.writeValue(response.writer, errorResponse)
    }
}
