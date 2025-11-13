package wee.sh.domain.auth.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wee.sh.domain.auth.presentation.dto.request.KakaoLoginRequest
import wee.sh.domain.auth.presentation.dto.response.LoginResponse
import wee.sh.domain.auth.service.AuthService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/kakao")
    fun kakaoLogin(@RequestBody request: KakaoLoginRequest): LoginResponse {
        return authService.loginWithKakao(request.accessToken)
    }
}
