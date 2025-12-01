package wee.sh.global.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.time.ZoneId
import java.time.ZonedDateTime

@Component
class DateRestrictionInterceptor : HandlerInterceptor {

    private val openAt: ZonedDateTime =
        ZonedDateTime.of(2025, 12, 24, 20, 30, 0, 0, ZoneId.of("Asia/Seoul"))

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
        if (now.isBefore(openAt)) {
            response.characterEncoding = "UTF-8"
            response.contentType = "text/plain; charset=UTF-8"
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.writer.write("해당 기능은 12월 24일 20시 30분 이후에 사용할 수 있습니다.")
            return false
        }
        return true
    }
}
