package wee.sh.infra.oauth2.kakao.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class KakaoConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}
