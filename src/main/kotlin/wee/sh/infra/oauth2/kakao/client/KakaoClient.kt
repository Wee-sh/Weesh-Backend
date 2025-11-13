package wee.sh.infra.oauth2.kakao.client

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import wee.sh.infra.oauth2.kakao.dto.KakaoTokenResponse
import wee.sh.infra.oauth2.kakao.dto.KakaoUserInfo
import wee.sh.infra.oauth2.kakao.exception.InvalidKakaoTokenException
import wee.sh.infra.oauth2.kakao.exception.KakaoApiErrorException
import wee.sh.infra.oauth2.kakao.exception.KakaoUserInfoNullException

@Component
class KakaoClient(
    private val restTemplate: RestTemplate // HTTP 요청을 보내는 도구
) {
    fun getAccessToken(
        code: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): String {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_FORM_URLENCODED }
        val body = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("client_id", clientId)
            add("client_secret", clientSecret)
            add("redirect_uri", redirectUri)
            add("code", code)
        }

        val request = HttpEntity(body, headers)

        return try {
            val response = restTemplate.postForEntity(
                "https://kauth.kakao.com/oauth/token",
                request,
                KakaoTokenResponse::class.java
            )
            response.body?.accessToken ?: throw KakaoApiErrorException
        } catch (e: RestClientException) {
            println("Kakao token request failed: ${e.message}")
            e.printStackTrace()
            throw KakaoApiErrorException
        }
    }

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
            println("Kakao token request failed: ${e.message}")
            e.printStackTrace()
            throw KakaoApiErrorException
        }
    }
}
