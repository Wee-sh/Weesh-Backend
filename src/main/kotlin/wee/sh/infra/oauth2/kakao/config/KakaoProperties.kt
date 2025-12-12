package wee.sh.infra.oauth2.kakao.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kakao")
class KakaoProperties {
    lateinit var clientId: String
    lateinit var clientSecret: String
}
