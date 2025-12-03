package wee.sh.infra.oauth2.kakao.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestClient

@Configuration
class KakaoConfig {

    /**
     * 기존에는 RestTemplate를 사용하려 했으나
     * Spring Boot / Kotlin 버전 호환 문제로 인해 정상 동작하지 않아 RestClient를 사용
     */
    @Bean
    fun restClient(): RestClient {
        val factory = SimpleClientHttpRequestFactory().apply {
            setConnectTimeout(5000)
            setReadTimeout(10000)
        }

        return RestClient.builder()
            .requestFactory(factory)
            .build()
    }
}
