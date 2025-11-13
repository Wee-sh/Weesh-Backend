package wee.sh.infra.oauth2.kakao.client

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import wee.sh.infra.oauth2.kakao.dto.KakaoUserInfo
import wee.sh.infra.oauth2.kakao.exception.InvalidKakaoTokenException
import wee.sh.infra.oauth2.kakao.exception.KakaoApiErrorException
import wee.sh.infra.oauth2.kakao.exception.KakaoUserInfoNullException

@Component
class KakaoClient(
    private val restTemplate: RestTemplate // HTTP 요청을 보내는 도구
) {
    fun getUserInfo(accessToken: String): KakaoUserInfo {
        val headers = HttpHeaders().apply {
            set("Authorization", "Bearer $accessToken")
        }

        return try {
            val response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                HttpEntity(null, headers),
                KakaoUserInfo::class.java
            )

            response.body ?: throw KakaoUserInfoNullException
        } catch (e: HttpClientErrorException.Unauthorized) {
            throw InvalidKakaoTokenException
        } catch (e: RestClientException) {
            throw KakaoApiErrorException
        }
    }
}
