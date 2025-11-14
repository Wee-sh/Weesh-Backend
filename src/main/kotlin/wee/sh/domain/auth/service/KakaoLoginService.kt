package wee.sh.domain.auth.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.auth.presentation.dto.response.LoginResponse
import wee.sh.domain.user.domain.User
import wee.sh.domain.user.domain.repository.UserRepository
import wee.sh.global.security.jwt.JwtTokenProvider
import wee.sh.infra.oauth2.kakao.client.KakaoClient

@Service
class KakaoLoginService(
    private val userRepository: UserRepository,
    private val kakaoClient: KakaoClient,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun loginWithKakao(accessToken: String): LoginResponse {
        val kakaoUserInfo = kakaoClient.getUserInfo(accessToken)
        val kakaoId = kakaoUserInfo.toKakaoId()

        val user = userRepository.findByKakaoId(kakaoId)
            ?: userRepository.save(
                User.create(
                    kakaoId = kakaoId,
                    nickname = kakaoUserInfo.toNickname()
                )
            )

        val tokenResponse = jwtTokenProvider.generateTokens(user.id)

        return LoginResponse(
            token = tokenResponse,
            userId = user.id,
            nickname = user.nickname,
            templateId = user.templateId
        )
    }
}
