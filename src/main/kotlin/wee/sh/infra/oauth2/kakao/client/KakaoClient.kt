package wee.sh.infra.oauth2.kakao.client

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import wee.sh.infra.oauth2.kakao.dto.KakaoTokenResponse
import wee.sh.infra.oauth2.kakao.dto.KakaoUserInfo
import wee.sh.infra.oauth2.kakao.exception.InvalidKakaoTokenException
import wee.sh.infra.oauth2.kakao.exception.KakaoApiErrorException
import wee.sh.infra.oauth2.kakao.exception.KakaoUserInfoNullException

@Component
class KakaoClient(
    private val restClient: RestClient
) {
    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * 인증 코드를 통해 액세스 토큰 받기
     */
    fun getAccessToken(
        code: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): String {
        val body = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("client_id", clientId)
            add("client_secret", clientSecret)
            add("redirect_uri", redirectUri)
            add("code", code)
        }

        return try {
            val response = restClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body<KakaoTokenResponse>()

            response?.accessToken ?: run {
                log.error("카카오 토큰 응답이 null입니다")
                throw KakaoApiErrorException
            }
        } catch (e: KakaoApiErrorException) {
            throw e
        } catch (e: Exception) {
            log.error("카카오 토큰 요청 중 오류 발생: ${e.message}", e)
            throw KakaoApiErrorException
        }
    }

    /**
     * 액세스 토큰으로 사용자 정보 가져오기
     */
    fun getUserInfo(accessToken: String): KakaoUserInfo {
        return try {
            val response = restClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer $accessToken")
                .retrieve()
                .onStatus({ it.value() == 401 }) { _, _ ->
                    log.error("카카오 사용자 정보 조회 실패 - 유효하지 않은 토큰")
                    throw InvalidKakaoTokenException
                }
                .body<KakaoUserInfo>()

            response ?: run {
                log.error("카카오 사용자 정보 응답이 null입니다")
                throw KakaoUserInfoNullException
            }
        } catch (e: InvalidKakaoTokenException) {
            throw e
        } catch (e: KakaoUserInfoNullException) {
            throw e
        } catch (e: Exception) {
            log.error("카카오 사용자 정보 조회 중 오류 발생: ${e.message}", e)
            throw KakaoApiErrorException
        }
    }
}
