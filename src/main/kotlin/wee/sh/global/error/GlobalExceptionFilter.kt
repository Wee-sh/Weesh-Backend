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
            val errorCode = e.errorCode
            response.status = errorCode.status
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"

            val errorResponse = ErrorResponse.of(errorCode, errorCode.message)
            objectMapper.writeValue(response.writer, errorResponse)
        } catch (e: Exception) {
            println("e.message = ${e.message}")
            response.status = response.status
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"

            val errorResponse = ErrorResponse.of(
                ErrorCode.INTERNAL_SERVER_ERROR,
                e.message ?: "Unknown error"
            )
            objectMapper.writeValue(response.writer, errorResponse)
        }
    }
}
