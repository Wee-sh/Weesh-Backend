package wee.sh.domain.auth.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.auth.domain.repository.RefreshTokenRepository
import wee.sh.domain.auth.presentation.dto.response.TokenResponse
import wee.sh.domain.user.service.facade.UserFacade
import wee.sh.global.security.jwt.JwtTokenProvider

@Service
class ReissueService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun reissue(refreshToken: String): TokenResponse {
        val userId = jwtTokenProvider.validateRefreshToken(refreshToken)

        userFacade.findUserByTokenUserId(userId)

        refreshTokenRepository.deleteById(refreshToken)

        return jwtTokenProvider.generateTokens(userId)
    }
}
