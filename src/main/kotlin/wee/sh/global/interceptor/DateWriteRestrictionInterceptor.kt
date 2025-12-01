package wee.sh.global.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.time.LocalDateTime

@Component
class DateWriteRestrictionInterceptor : HandlerInterceptor {

    private val closeAt: LocalDateTime =
        LocalDateTime.of(2025, 12, 24, 20, 30)

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (request.method == "POST" && LocalDateTime.now().isAfter(closeAt)) {
            response.characterEncoding = "UTF-8"
            response.contentType = "text/plain; charset=UTF-8"
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.writer.write("12월 24일 20시 30분 이후에는 작성할 수 없습니다.")
            return false
        }
        return true
    }
}
