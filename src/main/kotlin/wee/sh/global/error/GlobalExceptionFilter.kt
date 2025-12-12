package wee.sh.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: WeeShException) {
            addCorsHeaders(request, response)
            val errorCode = e.errorCode
            response.status = errorCode.status
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            val errorResponse = ErrorResponse.of(errorCode, errorCode.message)
            objectMapper.writeValue(response.writer, errorResponse)
        } catch (e: Exception) {
            addCorsHeaders(request, response)
            response.status = ErrorCode.INTERNAL_SERVER_ERROR.status
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            val errorResponse = ErrorResponse.of(
                ErrorCode.INTERNAL_SERVER_ERROR,
                e.message ?: "Unknown error"
            )
            objectMapper.writeValue(response.writer, errorResponse)
        }
    }

    private fun addCorsHeaders(request: HttpServletRequest, response: HttpServletResponse) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin") ?: "*")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS")
        response.setHeader("Access-Control-Allow-Headers", "*")
        response.setHeader("Access-Control-Max-Age", "3600")
    }
}
