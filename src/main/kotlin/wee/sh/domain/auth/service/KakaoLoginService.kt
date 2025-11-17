package wee.sh.domain.auth.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.auth.presentation.dto.response.LoginResponse
import wee.sh.domain.tree.domain.Tree
import wee.sh.domain.tree.domain.repository.TreeRepository
import wee.sh.domain.user.domain.User
import wee.sh.domain.user.domain.repository.UserRepository
import wee.sh.global.security.jwt.JwtTokenProvider
import wee.sh.infra.oauth2.kakao.client.KakaoClient
import wee.sh.infra.oauth2.kakao.config.KakaoProperties

@Service
class KakaoLoginService(
    private val userRepository: UserRepository,
    private val treeRepository: TreeRepository,
    private val kakaoClient: KakaoClient,
    private val jwtTokenProvider: JwtTokenProvider,
    private val kakaoProperties: KakaoProperties
) {
    @Transactional
    fun loginWithKakao(code: String): LoginResponse {
        val accessToken = kakaoClient.getAccessToken(
            code = code,
            redirectUri = kakaoProperties.redirectUri,
            clientId = kakaoProperties.clientId,
            clientSecret = kakaoProperties.clientSecret
        )

        val kakaoUserInfo = kakaoClient.getUserInfo(accessToken)
        val kakaoId = kakaoUserInfo.toKakaoId()

        val user = userRepository.findByKakaoId(kakaoId)
            ?: createUserWithTree(kakaoId, kakaoUserInfo.toNickname())

        val tokenResponse = jwtTokenProvider.generateTokens(user.id)

        return LoginResponse(
            token = tokenResponse,
            userId = user.id,
            nickname = user.nickname,
            templateId = user.templateId
        )
    }

    private fun createUserWithTree(kakaoId: String, nickname: String): User {
        val user = userRepository.save(
            User.create(
                kakaoId = kakaoId,
                nickname = nickname
            )
        )

        treeRepository.save(Tree(user = user))

        return user
    }
}
