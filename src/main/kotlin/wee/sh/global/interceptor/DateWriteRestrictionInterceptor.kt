package wee.sh.global.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.time.ZoneId
import java.time.ZonedDateTime

@Component
class DateWriteRestrictionInterceptor : HandlerInterceptor {

    private val closeAt: ZonedDateTime =
        ZonedDateTime.of(2025, 12, 24, 20, 30, 0, 0, ZoneId.of("Asia/Seoul"))

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
        if (request.method == "POST" && now.isAfter(closeAt)) {
            response.characterEncoding = "UTF-8"
            response.contentType = "text/plain; charset=UTF-8"
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.writer.write("12월 24일 20시 30분 이후에는 작성할 수 없습니다.")
            return false
        }
        return true
    }
}
