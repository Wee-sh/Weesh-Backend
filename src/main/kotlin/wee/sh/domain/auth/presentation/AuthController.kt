package wee.sh.domain.auth.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wee.sh.domain.auth.presentation.dto.request.KakaoLoginRequest
import wee.sh.domain.auth.presentation.dto.response.LoginResponse
import wee.sh.domain.auth.service.AuthService
import wee.sh.infra.oauth2.kakao.client.KakaoClient
import wee.sh.infra.oauth2.kakao.config.KakaoProperties

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val kakaoClient: KakaoClient,
    private val kakaoProperties: KakaoProperties
) {

    @PostMapping("/kakao")
    fun kakaoLogin(@RequestBody request: KakaoLoginRequest): LoginResponse {
        val accessToken = kakaoClient.getAccessToken(
            request.code,
            kakaoProperties.redirectUri,
            kakaoProperties.clientId,
            kakaoProperties.clientSecret
        )

        return authService.loginWithKakao(accessToken)
    }
}
