package wee.sh.domain.auth.presentation

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wee.sh.domain.auth.presentation.dto.request.KakaoLoginRequest
import wee.sh.domain.auth.presentation.dto.request.ReissueRequest
import wee.sh.domain.auth.presentation.dto.response.LoginResponse
import wee.sh.domain.auth.presentation.dto.response.TokenResponse
import wee.sh.domain.auth.service.KakaoLoginService
import wee.sh.domain.auth.service.ReissueService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val kakaoLoginService: KakaoLoginService,
    private val reissueService: ReissueService
) {

    @PostMapping("/kakao")
    fun kakaoLogin(
        @Validated @RequestBody
        request: KakaoLoginRequest
    ): LoginResponse {
        return kakaoLoginService.loginWithKakao(request.code)
    }

    @PostMapping("/reissue")
    fun reissue(
        @Validated @RequestBody
        request: ReissueRequest
    ): TokenResponse {
        return reissueService.reissue(request.refreshToken)
    }
}
