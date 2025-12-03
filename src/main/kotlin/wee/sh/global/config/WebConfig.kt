package wee.sh.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import wee.sh.global.interceptor.DateRestrictionInterceptor
import wee.sh.global.interceptor.DateWriteRestrictionInterceptor

@Configuration
class WebConfig(
    private val dateRestrictionInterceptor: DateRestrictionInterceptor,
    private val dateWriteRestrictionInterceptor: DateWriteRestrictionInterceptor
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(dateRestrictionInterceptor)
            .addPathPatterns(
                "/compliment/sent",
                "/compliment/received",
                "/user/rank/**"
            )

        registry.addInterceptor(dateWriteRestrictionInterceptor)
            .addPathPatterns(
                "/compliment"
            )
    }
}
