package wee.sh.domain.auth.presentation.dto.response

data class LoginResponse(
    val token: TokenResponse,
    val userId: Long,
    val nickname: String,
    val templateId: Int
)
