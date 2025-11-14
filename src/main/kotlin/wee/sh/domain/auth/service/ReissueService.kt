package wee.sh.domain.auth.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.auth.domain.repository.RefreshTokenRepository
import wee.sh.domain.auth.presentation.dto.response.TokenResponse
import wee.sh.domain.user.domain.repository.UserRepository
import wee.sh.global.security.exception.InvalidTokenException
import wee.sh.global.security.jwt.JwtTokenProvider

@Service
class ReissueService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun reissue(refreshToken: String): TokenResponse {
        val userId = jwtTokenProvider.validateRefreshToken(refreshToken)

        userRepository.findByIdOrNull(userId)
            ?: throw InvalidTokenException

        refreshTokenRepository.deleteById(refreshToken)

        return jwtTokenProvider.generateTokens(userId)
    }
}
