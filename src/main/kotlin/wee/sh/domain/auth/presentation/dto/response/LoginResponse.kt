package wee.sh.domain.auth.presentation.dto.response

import wee.sh.domain.user.domain.User

data class LoginResponse(
    val token: TokenResponse,
    val userId: Long,
    val nickname: String,
    val templateId: Int
) {
    companion object {
        fun of(user: User, token: TokenResponse): LoginResponse {
            return LoginResponse(
                token = token,
                userId = user.id,
                nickname = user.nickname,
                templateId = user.templateId
            )
        }
    }
}
